package DecisionEngine.GameObject;

import java.util.ArrayList;

public abstract class StateNode implements StateNodeInterface {
    StateMapInterface map;
    ArrayList<StateLinkInterface> links;
    
    public StateNode(StateMapInterface map, ArrayList<StateLinkInterface> links) throws NullPointerException {
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
        this.links = new ArrayList<StateLinkInterface>();
    }

    public abstract void behaviour();
}
