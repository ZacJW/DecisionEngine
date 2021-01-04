package DecisionEngine.GameObject;

import java.util.Collection;

public interface GameObjectInterface extends Runnable {
    public Collection<StateMapInterface> getStateMaps();

    /**
     * Initialises the object so it can be rendered.
     * 
     * All calls to OpenGL should be done here so it can be called by the main thread.
     */
    public void initialise();
}
