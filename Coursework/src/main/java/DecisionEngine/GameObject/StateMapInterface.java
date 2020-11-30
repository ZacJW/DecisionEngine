package DecisionEngine.GameObject;

import java.util.Collection;

public interface StateMapInterface {
    StateNodeInterface getActiveNode();
    Collection<? extends StateNodeInterface> getNodes();
}
