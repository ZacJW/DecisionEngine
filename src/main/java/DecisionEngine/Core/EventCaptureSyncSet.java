package DecisionEngine.Core;

import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import DecisionEngine.Event.GameEventInterface;

public class EventCaptureSyncSet implements EventCaptureInterface {
    Set<GameEventInterface> set;
    public EventCaptureSyncSet(){
        set = Collections.synchronizedSet(new HashSet<GameEventInterface>());
    }
    public void add(GameEventInterface event){
        set.add(event);
    }
    public void add(Collection<GameEventInterface> events){
        set.addAll(events);
    }
    public Iterator<GameEventInterface> iterator(){
        return set.iterator();
    }
    public void reset(){
        set.clear();
    }
}
