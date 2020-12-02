package DecisionEngine.Core;

import java.util.Collection;
import java.util.Iterator;

import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.Utils.ConcurrentTree;

public class EventCaptureCCTree implements EventCaptureInterface {
    ConcurrentTree<GameEventInterface> tree;
    public EventCaptureCCTree(){
        tree = new ConcurrentTree<GameEventInterface>();
    }
    public void add(GameEventInterface event){
        tree.add(event);
    }
    public void add(Collection<GameEventInterface> events){
        for (GameEventInterface event : events){
            tree.add(event);
        }
    }
    public Iterator<GameEventInterface> iterator(){
        return tree.iterator();
    }
    public void reset(){
        tree.clear();
    }
}
