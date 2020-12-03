package DecisionEngine.Event;

import java.util.Collection;

import DecisionEngine.GameObject.StateLinkInterface;

/**
 * The interface that all game events must implement.
 */
public interface GameEventInterface extends Runnable {
    /**
     * Get a Collection of all StateLinkInterface objects currently registered on this event
     * @return a Collection of all StateLinkInterface objects currently registered on this event
     */
    public Collection<? extends StateLinkInterface> getLinks();
    /**
     * Get all events listening to this event
     * @return a Collection of all GameEventInterface objects listening to this event
     */
    public Collection<? extends GameEventInterface> getListeners();
    /**
     * Checks the event's trigger condition and returns it
     * @return a boolean of whether the event's trigger condition is met
     */
    public boolean check();
    /**
     * Triggers the event, registering state updates for connected StateLinkInterface objects
     */
    public void trigger();
}
