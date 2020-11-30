package DecisionEngine;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.Core.LocalEventSet;
import DecisionEngine.Event.GameEventInterface;
import DecisionEngine.GameObject.StateLinkInterface;
import DecisionEngine.Listener.ListenPoint;
import DecisionEngine.Listener.ListenedVar;

public class ListenedVarTest {
    @Test
    public void listenedVarEventCheckTest(){
        EventCapture ec = new EventCapture();
        ListenedVar<String> var = new ListenedVar<String>(ec, "Hello");
        ListenPoint<String> lp = var.getListenPoint();
        TestEvent event = new TestEvent(lp);
        lp.register(event);
        var.set("World");
        Set<GameEventInterface> testSet = new HashSet<GameEventInterface>();
        testSet.add(event);
        assertTrue(ec.getEventCheckSet().equals(testSet));
    }
}

class EventCapture implements EventCaptureInterface {
    LocalEventSet eventCheckSetLocal;

    EventCapture(){
        eventCheckSetLocal = new LocalEventSet();
    }

    public Set<GameEventInterface> getEventCheckSet(){
        return eventCheckSetLocal.get();
    }

    public void resetEventCheckSet(){
        eventCheckSetLocal.set(new HashSet<GameEventInterface>());
    }

    public void registerEventChecks(Set<GameEventInterface> events){
        eventCheckSetLocal.get().addAll(events);
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
}