package DecisionEngine.GameObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StateMapTest {
    @Test
    public void activeNodeTest() throws NullPointerException{
        StateMap map = new StateMap();
        StateNodeInterface node1 = new Node(map);
        map.pushNode(node1);
        StateNodeInterface node2 = new Node(map);
        map.pushNode(node2);
        map.setActiveNode(node1);
        assertEquals(map.getActiveNode(), node1);
        assertNotEquals(map.getActiveNode(), node2);
        assertTrue(node1.isActive());
        assertFalse(node2.isActive());
        map.setActiveNode(node2);
        assertEquals(map.getActiveNode(), node2);
        assertNotEquals(map.getActiveNode(), node1);
        assertTrue(node2.isActive());
        assertFalse(node1.isActive());
    }

    @Test
    public void getNodesTest(){
        StateMap map = new StateMap();
        for (int i=0; i<10; i++){
            map.pushNode(new Node(map));
        }
        assertEquals(map.nodes.size(), 10);
    }
}

class Node extends StateNode{
    public Node(StateMapInterface map) throws NullPointerException {
        super(map);
    }

    public void behaviour() {

    }
}