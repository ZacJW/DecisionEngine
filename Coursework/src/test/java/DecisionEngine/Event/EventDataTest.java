package DecisionEngine.Event;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import DecisionEngine.GameObject.StateLinkDataInterface;
import DecisionEngine.GameObject.StateNodeDataInterface;

public class EventDataTest {
    @Test
    public void eventDataTest(){

    }
}

class TestEvent implements GameEventDataInterface<String> {
    Set<StateLinkDataInterface<String>> links;
    TestEvent(StateLinkDataInterface<String> link){
        links = new HashSet<StateLinkDataInterface<String>>();
        links.add(link);
    }
    public boolean check(){
        return true;
    }
    public void trigger(){

    }
    public Set<GameEventInterface> getListeners(){
        return new HashSet<GameEventInterface>();
    }
    public Set<StateLinkDataInterface<String>> getLinks(){
        return links;
    }

    public void updateState(StateNodeDataInterface<String> node){

    }
    public void run(){
        
    }

}
