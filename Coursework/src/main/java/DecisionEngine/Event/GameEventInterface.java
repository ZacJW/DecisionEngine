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
     * Adds a link to the event to be triggered when the event is triggered
     * @param link the link to be added
     */
    public void addLink(StateLinkInterface link);
    /**
     * Removes a link from the event so it is no longer triggered when the event is triggered
     * @param link the link to be removed
     */
    public void removeLink(StateLinkInterface link);
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
