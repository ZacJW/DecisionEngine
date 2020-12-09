package DecisionEngine.GameObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import DecisionEngine.Core.World;

public class FullStateTest {
    @Test
    public void fullStateTest(){
        Set<String> output = Collections.synchronizedSet(new HashSet<String>());
        TestWorld world = new TestWorld();
        StateMap map = new StateMap();
        TestNode node1 = new TestNode(map, "Node1", output);
        map.pushNode(node1);

    }
}

class TestNode extends StateNode{
    String name;
    Set<String> out;
    public TestNode(StateMapInterface map, String name, Set<String> out) throws NullPointerException {
        super(map);
        this.name = name;
        this.out = out;
    }

    public void behaviour() {
        out.add(name + " behaviour");
    }

    public void addLink(StateLinkInterface link, Integer priority) {
        links.put(priority, link);
    }
}

class TestWorld extends World{
    public TestWorld() {
        super();
    }
    public void doBehaviours(){
        processBehaviours();
    }
    public void doEvents(){
        processEvents();
    }
    public void doStateUpdate(){
        updateStates();
    }
}