package DecisionEngine.Core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.GameObject.GameObjectInterface;
import DecisionEngine.GameObject.StateLinkInterface;
import DecisionEngine.GameObject.StateNodeInterface;

public abstract class World implements WorldInterface {
    Map<GameObjectInterface, ObjectWorldData> gameObjects;
    EventCaptureInterface uncheckedEvents;
    StateUpdateInterface pendingStates;

    public World() {
        gameObjects = new HashMap<GameObjectInterface, ObjectWorldData>();
        uncheckedEvents = new EventCaptureSyncSet();
        pendingStates = new StateUpdateSyncMap();
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

    public void addStateUpdate(StateNodeInterface node, StateLinkInterface link){
        pendingStates.add(node, link);
    }
}
