package DecisionEngine.Listener;

import java.util.HashSet;
import java.util.Set;

import DecisionEngine.Event.GameEventInterface;

public class ListenPoint<T> {
    ListenedVar<T> listenedVar;
    Set<GameEventInterface> listeners;

    ListenPoint(ListenedVar<T> listenedVar){
        this.listenedVar = listenedVar;
        listeners = new HashSet<GameEventInterface>();
    }

    public T get(){
        return listenedVar.get();
    }

    public void register(GameEventInterface event){
        listeners.add(event);
    }

    public void unregister(GameEventInterface event){
        try{
            listeners.remove(event);
        }catch (NullPointerException e){
            
        }
    }
}
