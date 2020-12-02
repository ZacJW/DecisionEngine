package DecisionEngine.Core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.GameObject.GameObjectInterface;
import DecisionEngine.GameObject.StateNodeInterface;

public abstract class World {
    //ArrayList<GameObject> gameObjects;
    Set<GameObjectInterface> gameObjects;
    EventCaptureInterface uncheckedEvents;
    StateUpdateInterface pendingStates;

    public World() {
        gameObjects = new HashSet<GameObjectInterface>();
        uncheckedEvents = new EventCaptureSyncSet();
        pendingStates = new StateUpdateSyncMap();
    }

    public EventCaptureInterface getEventCapture(){
        return uncheckedEvents;
    }

    protected void processBehaviours(){
        uncheckedEvents.reset();
        ExecutorService pool = Executors.newCachedThreadPool();
        for (GameObjectInterface gameObject : gameObjects){
            pool.execute(gameObject);
        }
        pool.shutdown();
        try{
            pool.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }
    }

    protected void processEvents(){
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

    protected void updateStates(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for (StateNodeInterface node : pendingStates){
            //pool.submit(task);
        }
        pool.shutdown();
        try{
            pool.awaitTermination(1, TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }
    }
}
