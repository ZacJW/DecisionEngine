package DecisionEngine.GameObject;

import DecisionEngine.Utils.BiMap;

public abstract class StateNode implements StateNodeInterface {
    protected StateMapInterface map;
    protected BiMap<Integer, StateLinkInterface> links;
    
    public StateNode(StateMapInterface map, BiMap<Integer, StateLinkInterface> links) throws NullPointerException {
        if (map == null){
            throw new NullPointerException("map cannot be null");
        }
        this.map = map;
        this.links = links;
    }

    public StateNode(StateMapInterface map) throws NullPointerException {
        if (map == null){
            throw new NullPointerException("map cannot be null");
        }
        this.map = map;
        this.links = new BiMap<Integer, StateLinkInterface>();
    }

    public boolean isActive(){
        return this == map.getActiveNode();
    }

    public BiMap<Integer, StateLinkInterface> getLinks(){
        return links;
    }

    public StateMapInterface getMap(){
        return map;
    }

    public abstract void behaviour();
}
