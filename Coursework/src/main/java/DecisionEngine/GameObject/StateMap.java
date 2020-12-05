package DecisionEngine.GameObject;

import java.util.ArrayList;
import java.util.Collection;

public class StateMap implements StateMapInterface {
    StateNodeInterface activeNode;
    ArrayList<StateNodeInterface> nodes;

    public StateMap(StateNodeInterface activeNode, ArrayList<StateNodeInterface> nodes) throws NullPointerException {
        if (activeNode == null) {
            throw new NullPointerException("activeNode cannot be null");
        }
        this.activeNode = activeNode;
        this.nodes = nodes;
    }

    public StateMap() {
        this.nodes = new ArrayList<StateNodeInterface>();
    }

    public void pushNode(StateNodeInterface node) {
        nodes.add(node);
    }

    public StateNodeInterface popNode() {
        int lastIndex = nodes.size() - 1;
        StateNodeInterface node = nodes.get(lastIndex);
        nodes.remove(lastIndex);
        return node;
    }

    public StateNodeInterface getActiveNode() {
        return activeNode;
    }

    public Collection<? extends StateNodeInterface> getNodes() {
        return nodes;
    }

    public void setActiveNode(StateNodeInterface node) {
        activeNode = node;
    }
}
