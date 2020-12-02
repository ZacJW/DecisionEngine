package DecisionEngine.Core;

import java.util.Iterator;

import DecisionEngine.GameObject.StateLinkInterface;
import DecisionEngine.GameObject.StateNodeInterface;

public interface StateUpdateInterface extends Iterable<StateNodeInterface> {
    public void add(StateNodeInterface node, StateLinkInterface link);
    public Iterator<StateNodeInterface> iterator();
    public void reset();
}
