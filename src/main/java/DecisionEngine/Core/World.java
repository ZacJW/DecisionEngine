package DecisionEngine.Core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

import org.ejml.data.FMatrixRMaj;
import org.ejml.simple.SimpleMatrix;

import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.GameObject.GameObjectInterface;
import DecisionEngine.GameObject.StateLinkInterface;
import DecisionEngine.GameObject.StateNodeInterface;
import DecisionEngine.Input.Input;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;
import DecisionEngine.Render.RendererInterface;

public abstract class World implements WorldInterface {
    volatile Map<GameObjectInterface, ObjectWorldData> gameObjects;
    volatile EventCaptureInterface uncheckedEvents;
    volatile StateUpdateInterface pendingStates;
    volatile PositionUpdate updatedPositions = new PositionUpdate();
    volatile Map<GameObjectInterface, ObjectWorldData> spawningObjects = Collections.synchronizedMap(new HashMap<GameObjectInterface, ObjectWorldData>());
    Input input;
    RendererInterface renderer;
    LWJGLInterface lwjgl;
    boolean initialised = false;

    public World(LWJGLInterface lwjgl, RendererInterface renderer) {
        gameObjects = new HashMap<GameObjectInterface, ObjectWorldData>();
        uncheckedEvents = new EventCaptureSyncSet();
        pendingStates = new StateUpdateSyncMap();
        this.renderer = renderer;
        this.lwjgl = lwjgl;
    }

    public void initialise(){
        if (initialised){
            return;
        }
        renderer.initialise();
        input = new Input(uncheckedEvents, lwjgl, renderer.getWindow());
    }

    public void updatePosition(GameObjectInterface gameObject, SimpleMatrix position){
        ObjectWorldData data = gameObjects.get(gameObject);
        data.updatePosition(position);
        updatedPositions.add(data);
    }

    public SimpleMatrix getPosition(GameObjectInterface gameObject){
        return gameObjects.get(gameObject).position.copy();
    }

    public EventCaptureInterface getEventCapture(){
        return uncheckedEvents;
    }

    public void processBehaviours(){
        uncheckedEvents.reset();
        ExecutorService pool = Executors.newCachedThreadPool();
        for (Entry<GameObjectInterface, ObjectWorldData> entry : gameObjects.entrySet()){
            pool.execute(entry.getKey());
        }
        pool.shutdown();
        try{
            pool.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }
    }

    public void processEvents(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for (GameEventInterface event : uncheckedEvents){
            pool.submit(event);
        }
        pool.shutdown();
        try{
            pool.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }
    }

    public void updateStates(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for (StateNodeInterface node : pendingStates){
            pool.submit(pendingStates.getLink(node));
        }
        pool.shutdown();
        try{
            pool.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }
    }

    public void spawnObjects(){
        for (GameObjectInterface gameObject : spawningObjects.keySet()){
            gameObjects.put(gameObject, spawningObjects.get(gameObject));
            gameObject.initialise();
            updatedPositions.add(spawningObjects.get(gameObject));
        }
        spawningObjects.clear();
    }

    public void addObject(GameObjectInterface gameObject){
        addObject(gameObject, SimpleMatrix.identity(4, FMatrixRMaj.class));
    }

    public void addObject(GameObjectInterface gameObject, SimpleMatrix position){
        if (gameObject == null){
            throw new NullPointerException();
        }
        if (!gameObjects.containsKey(gameObject)){
            ObjectWorldData objectData = new ObjectWorldData(position);
            gameObjects.put(gameObject, objectData);
            updatedPositions.add(objectData);
        }
    }

    public void processPositions(){
        for (ObjectWorldData objectData : updatedPositions.topLevelObjects){
            _processPosition(objectData);
        }
        updatedPositions.topLevelObjects.clear();
        updatedPositions.parents.clear();
    }

    private void _processPosition(ObjectWorldData objectData){
        if (objectData.parent == null){
            objectData.globalPosition = objectData.position;
        }else{
            objectData.globalPosition = objectData.parent.globalPosition.mult(objectData.position);
        }
        for (ObjectWorldData childData : objectData.children){
            _processPosition(childData);
        }
    }

    public void setFamily(GameObjectInterface parent, GameObjectInterface child){
        ObjectWorldData childData = gameObjects.get(child);
        ObjectWorldData oldParentData = childData.parent;
        ObjectWorldData parentData = gameObjects.get(parent);

        if (parentData == null){
            // Remove parent from child

            if(oldParentData == null){
                // Child is already without a parent, so return
                return;
            }
            oldParentData.children.remove(childData);
            childData.parent = null;
        }else{
            // Move child to new parent

            // Check if parent is already one of child's children/grandchildren
            ObjectWorldData grandParentData = parentData.parent;
            while (grandParentData != null){
                if (grandParentData == childData){
                    throw new RuntimeException("child is already grandparent of parent");
                }
                grandParentData = grandParentData.parent;
            }

            if(oldParentData != null){
                oldParentData.children.remove(childData);
            }
            parentData.children.add(childData);
            childData.parent = parentData;
        }
        updatedPositions.migrateObject(oldParentData, childData);
        
    }

    public void requestSpawn(GameObjectInterface gameObject, GameObjectInterface parent, SimpleMatrix position){
        if (position == null){
            position = SimpleMatrix.identity(4, FMatrixRMaj.class);
        }else if (gameObjects.containsKey(gameObject)){
            throw new RuntimeException("gameObject is already spawned");
        }
        ObjectWorldData objectData = new ObjectWorldData(position);
        if (parent != null){
            ObjectWorldData parentData;
            if (!gameObjects.containsKey(parent)){
                if (!spawningObjects.containsKey(parent)){
                    throw new RuntimeException("parent is not spawned or being spawned, and so is invalid");
                }else{
                    parentData = spawningObjects.get(parent);
                }
            }else{
                parentData = gameObjects.get(parent);
            }
            objectData.parent = parentData;
            parentData.children.add(objectData);
        }
        spawningObjects.put(gameObject, objectData);

    }

    public void addStateUpdate(StateNodeInterface node, StateLinkInterface link){
        pendingStates.add(node, link);
    }

    public Input getInput(){
        return input;
    }

    public RendererInterface getRenderer(){
        return renderer;
    }
}
