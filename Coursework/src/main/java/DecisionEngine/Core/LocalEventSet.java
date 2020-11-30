package DecisionEngine.Core;

import java.util.HashSet;
import java.util.Set;

import DecisionEngine.Event.GameEventInterface;

public class LocalEventSet extends ThreadLocal<Set<GameEventInterface>> {
    @Override
    protected Set<GameEventInterface> initialValue() {
        return new HashSet<GameEventInterface>();
    }
}