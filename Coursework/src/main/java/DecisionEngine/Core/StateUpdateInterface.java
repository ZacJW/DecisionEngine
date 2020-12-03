package DecisionEngine.Core;

import java.util.Iterator;

import DecisionEngine.GameObject.StateLinkInterface;
import DecisionEngine.GameObject.StateNodeInterface;

/**
 * The interface for capturing state changes in StateMapInterface objects
 */
public interface StateUpdateInterface extends Iterable<StateNodeInterface> {
    /**
     * Captures a state node and state link to be updated at the next state update step
     * If node is already captured, the priority of the existing link and new link are
     * compared, and the higer priority link is kept in the capture.
     * @param node The currently active StateNodeInterface with a triggered link
     * @param link The triggered StateLinkInterface from node
     */
    public void add(StateNodeInterface node, StateLinkInterface link);
    public Iterator<StateNodeInterface> iterator();
    /**
     * Empties the state update capture
     */
    public void reset();
}
