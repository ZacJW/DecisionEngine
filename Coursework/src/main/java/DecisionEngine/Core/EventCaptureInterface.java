package DecisionEngine.Core;

import java.util.Collection;
import java.util.Iterator;

import DecisionEngine.Event.GameEventInterface;

/**
 * The interface for capturing game events that need to be re-checked when their listened variables change.
 * Must be thread-safe
 */
public interface EventCaptureInterface extends Iterable<GameEventInterface> {
    /**
     * Captures an event to be re-processed later
     * @param event the event to be re-processed at the next event step
     */
    public void add(GameEventInterface event);
    /**
     * Captures many events to be re-processed later
     * @param events a Collection of events to be re-processed at the next event step
     */
    public void add(Collection<GameEventInterface> events);
    public Iterator<GameEventInterface> iterator();
    /**
     * Empties the event capture
     */
    void reset();
}
