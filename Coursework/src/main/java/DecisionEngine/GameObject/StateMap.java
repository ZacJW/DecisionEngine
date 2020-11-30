package DecisionEngine.GameObject;

import java.util.ArrayList;

public class StateMap {
    StateNode activeNode;
    ArrayList<StateNode> nodes;
    
    public StateMap(StateNode activeNode, ArrayList<StateNode> nodes) throws NullPointerException {
        if (activeNode == null) {
            throw new NullPointerException("activeNode cannot be null");
        }
        this.activeNode = activeNode;
        this.nodes = nodes;
    }

    public StateMap(){
        this.nodes = new ArrayList<StateNode>();
    }

    public void pushNode(StateNode node) {
        nodes.add(node);
    }

    public StateNode popNode() {
        int lastIndex = nodes.size()-1;
        StateNode node = nodes.get(lastIndex);
        nodes.remove(lastIndex);
        return node;
    }
}
