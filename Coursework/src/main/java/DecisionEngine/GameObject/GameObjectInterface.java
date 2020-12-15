package DecisionEngine.GameObject;

import java.util.Collection;

public interface GameObjectInterface extends Runnable {
    public Collection<StateMapInterface> getStateMaps();
    public Collection<GameObjectInterface> getChildren();
    public GameObjectInterface getParent();
}
