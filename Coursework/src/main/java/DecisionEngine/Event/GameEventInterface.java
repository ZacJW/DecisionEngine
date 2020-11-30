package DecisionEngine.Event;

import java.util.Collection;

import DecisionEngine.GameObject.StateLinkInterface;

public interface GameEventInterface {
    public Collection<? extends StateLinkInterface> getLinks();
    public Collection<? extends GameEventInterface> getListeners();
    public boolean check();
    public void trigger();
}
