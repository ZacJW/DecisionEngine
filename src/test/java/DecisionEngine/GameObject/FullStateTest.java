package DecisionEngine.GameObject;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import DecisionEngine.Core.World;
import DecisionEngine.Event.GameEvent;
import DecisionEngine.LWJGLDelegate.LWJGLDummy;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;
import DecisionEngine.Render.RendererInterface;

public class FullStateTest {
    @Test
    public void fullStateTest(){
        Set<String> output = Collections.synchronizedSet(new HashSet<String>());
        DummyRenderer renderer = new DummyRenderer();
        TestWorld world = new TestWorld(new LWJGLDummy(), renderer);
        StateMap map = new StateMap();
        TestNode node1 = new TestNode(map, "Node1", output);
        map.pushNode(node1);
        TestNode node2 = new TestNode(map, "Node2", output);
        map.pushNode(node2);
        TestNode node3 = new TestNode(map, "Node3", output);
        map.pushNode(node3);
        map.setActiveNode(node1);
        TestEvent event1 = new TestEvent(world);
        event1.setState(true);
        StateLink link1 = new StateLink(node2, node1, event1);
        event1.addLink(link1);
        TestEvent event2 = new TestEvent(world);
        StateLink link2 = new StateLink(node3, node1, event2);
        event2.addLink(link2);
        node1.addLink(link1, 1);
        node1.addLink(link2, 2);
        assertEquals(node1, map.getActiveNode());
        world.getEventCapture().add(event1);
        world.doEvents();
        world.doStateUpdate();
        assertEquals(node2, map.getActiveNode());
    }
}

class DummyRenderer implements RendererInterface {

    @Override
    public void initialise() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean shouldTerminate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void pollEvents() {
        // TODO Auto-generated method stub

    }

    @Override
    public void terminate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void renderAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public long getWindow() {
        // TODO Auto-generated method stub
        return 0;
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
    public TestWorld(LWJGLInterface lwjgl, RendererInterface renderer) {
        super(lwjgl, renderer);
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

    @Override
    public void mainloop() {
        // TODO Auto-generated method stub

    }
}

class TestEvent extends GameEvent {
    boolean state = false;
    public TestEvent(World world) {
        super(world);
        
    }
    public void setState(boolean state){
        this.state = state;
    }
    public boolean check() {
        return state;
    }

}