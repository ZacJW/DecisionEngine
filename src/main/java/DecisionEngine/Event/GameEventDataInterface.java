package DecisionEngine.Event;

import java.util.Collection;

import DecisionEngine.GameObject.StateLinkDataInterface;

public interface GameEventDataInterface<T> extends GameEventInterface {
    public Collection<? extends StateLinkDataInterface<T>> getLinks();
}
