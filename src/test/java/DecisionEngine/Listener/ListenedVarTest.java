package DecisionEngine.Listener;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.Core.EventCaptureSyncSet;
import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.GameObject.StateLinkInterface;

public class ListenedVarTest {
    @Test
    public void listenedVarEventCheckTest(){
        EventCaptureInterface ec = new EventCaptureSyncSet();
        ListenedVar<String> var = new ListenedVar<String>(ec, "Hello");
        ListenPoint<String> lp = var.getListenPoint();
        TestEvent event = new TestEvent(lp);
        lp.registerEvent(event);
        var.set("World");
        Set<GameEventInterface> testSet = new HashSet<GameEventInterface>();
        testSet.add(event);
        HashSet<GameEventInterface> resultSet = new HashSet<GameEventInterface>();
        for (GameEventInterface resultEvent : ec){
            resultSet.add(resultEvent);
        }
        assertTrue(resultSet.equals(testSet));
    }
}

class TestEvent implements GameEventInterface {
    boolean triggered;
    String captureVar;
    ListenPoint<String> lp;

    TestEvent(ListenPoint<String> lp){
        triggered = false;
        this.lp = lp;
    }
    public Set<StateLinkInterface> getLinks(){
        return new HashSet<StateLinkInterface>();
    }

    public Set<GameEventInterface> getListeners(){
        return new HashSet<GameEventInterface>();
    }

    public boolean check(){
        return true;
    }

    public void trigger(){
        triggered = true;
        captureVar = lp.get();
    }

    public void run(){

    }

    @Override
    public void addLink(StateLinkInterface link) {

    }

    @Override
    public void removeLink(StateLinkInterface link) {

    }
}