package DecisionEngine.GameObject;

import DecisionEngine.Event.GameEventDataInterface;

public interface StateLinkDataInterface<T> extends StateLinkInterface {
    StateNodeDataInterface<T> getTo();
    GameEventDataInterface<T> getEvent();
}
