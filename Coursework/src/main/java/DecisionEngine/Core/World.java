package DecisionEngine.Core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.GameObject.GameObject;

public abstract class World implements EventCaptureInterface {
    //ArrayList<GameObject> gameObjects;
    Set<GameObject> gameObjects;
    LocalEventSet eventCheckSetLocal;
    Set<GameEventInterface> eventCheckSet;

    public World() {
        gameObjects = new HashSet<GameObject>();
        eventCheckSetLocal = new LocalEventSet();
        eventCheckSet = new HashSet<GameEventInterface>();
    }

    public Set<GameEventInterface> getEventCheckSet(){
        return eventCheckSetLocal.get();
    }

    public void resetEventCheckSet(){
        eventCheckSetLocal.set(new HashSet<GameEventInterface>());
    }

    public void registerEventChecks(Set<GameEventInterface> events){
        eventCheckSetLocal.get().addAll(events);
    }

    protected void processBehaviours(){
        GameObject[] gameObjectArray = (GameObject[]) gameObjects.toArray();
        ArrayList<Future<Set<GameEventInterface>>> results = new ArrayList<Future<Set<GameEventInterface>>>(gameObjects.size());
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < gameObjectArray.length; i++){
            results.set(i, pool.submit(gameObjectArray[i]));
        }
        for (Future<Set<GameEventInterface>> result : results){
            try{
                eventCheckSet.addAll(result.get());
            }catch (InterruptedException | ExecutionException e){

            }
        }
    }
}
