package DecisionEngine.GameObject;

import DecisionEngine.Event.GameEventInterface;

public interface StateLinkInterface {
    StateNodeInterface getTo();
    StateNodeInterface getFrom();
    GameEventInterface getEvent();
}
