package DecisionEngine.GameObject;

import java.util.ArrayList;

public abstract class StateNode {
    StateMap map;
    ArrayList<StateLink> links;
    
    public StateNode(StateMap map, ArrayList<StateLink> links) throws NullPointerException {
        if (map == null){
            throw new NullPointerException("map cannot be null");
        }
        this.map = map;
        this.links = links;
    }

    public StateNode(StateMap map) throws NullPointerException {
        if (map == null){
            throw new NullPointerException("map cannot be null");
        }
        this.map = map;
        this.links = new ArrayList<StateLink>();
    }

    abstract void behaviour();
}
