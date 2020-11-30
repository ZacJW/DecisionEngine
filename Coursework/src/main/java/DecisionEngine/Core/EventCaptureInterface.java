package DecisionEngine.Core;

import java.util.Set;

import DecisionEngine.Event.GameEventInterface;

public interface EventCaptureInterface {
    public Set<GameEventInterface> getEventCheckSet();
    public void resetEventCheckSet();
    public void registerEventChecks(Set<GameEventInterface> events);
}
