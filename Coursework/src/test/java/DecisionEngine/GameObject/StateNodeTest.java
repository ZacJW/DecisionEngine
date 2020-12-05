package DecisionEngine.GameObject;

import static org.junit.Assert.assertEquals;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.Test;

import DecisionEngine.Utils.BiMap;

public class StateNodeTest {
    @Test
    public void getLinksTest(){
        StateMap map = new StateMap();
        Node1 node1 = new Node1(map);
        StateNode node2 = new Node1(map);
        StateNode node3 = new Node1(map);
        StateLinkInterface link1 = new StateLink(node1, node2, null);
        StateLinkInterface link2 = new StateLink(node1, node3, null);
        node1.addLink(link1, 1);
        node1.addLink(link2, 2);
        BiMap<Integer, StateLinkInterface> links = node1.getLinks();
        assertEquals(link1, links.getValue(1));
        assertEquals(link2, links.getValue(2));
    }
}

class Node1 extends StateNode{
    public Node1(StateMapInterface map) throws NullPointerException {
        super(map);
    }

    public void behaviour() {

    }

    public void addLink(StateLinkInterface link, Integer priority) throws KeyAlreadyExistsException{
        links.put(priority, link);
    }
}