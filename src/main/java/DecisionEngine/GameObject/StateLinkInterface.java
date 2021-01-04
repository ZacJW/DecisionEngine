package DecisionEngine.GameObject;

import DecisionEngine.Event.GameEventInterface;

public interface StateLinkInterface extends Runnable {
    StateNodeInterface getTo();
    StateNodeInterface getFrom();
    GameEventInterface getEvent();
}
