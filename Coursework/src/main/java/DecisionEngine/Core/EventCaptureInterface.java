package DecisionEngine.Core;

import java.util.Collection;
import java.util.Iterator;

import DecisionEngine.Event.GameEventInterface;

public interface EventCaptureInterface extends Iterable<GameEventInterface> {
    public void add(GameEventInterface event);
    public void add(Collection<GameEventInterface> events);
    public Iterator<GameEventInterface> iterator();
    void reset();
}
