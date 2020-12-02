package DecisionEngine.Core;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import DecisionEngine.GameObject.StateLinkInterface;
import DecisionEngine.GameObject.StateNodeInterface;
import DecisionEngine.Utils.BiMap;

public class StateUpdateSyncMap implements StateUpdateInterface {
    ConcurrentMap<StateNodeInterface, StateLinkInterface> map;
    public StateUpdateSyncMap(){
        map = new ConcurrentHashMap<StateNodeInterface, StateLinkInterface>();
    }
    public void add(StateNodeInterface node, StateLinkInterface link){
        map.merge(node, link, StateUpdateSyncMap::mergeLink);
        map.put(node, link);
    }
    public void reset(){
        map.clear();
    }
    public Iterator<StateNodeInterface> iterator(){
        return map.keySet().iterator();
    }
    public static StateLinkInterface mergeLink(StateLinkInterface link1, StateLinkInterface link2){
        BiMap<Integer, StateLinkInterface> priorityMap = link1.getFrom().getLinks();
        if (priorityMap.getKey(link1) < priorityMap.getKey(link2)){
            return link1;
        }else{
            return link2;
        }
    }
}
