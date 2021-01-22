package DecisionEngine.Core;

import org.ejml.simple.SimpleMatrix;

import DecisionEngine.GameObject.GameObjectInterface;
import DecisionEngine.Input.Input;
import DecisionEngine.Render.RendererInterface;

public interface WorldInterface {

    public void updatePosition(GameObjectInterface gameObject, SimpleMatrix position);

    public SimpleMatrix getLocalPosition(GameObjectInterface gameObject);

    public SimpleMatrix getGlobalPosition(GameObjectInterface gameObject);

    public void requestSpawn(GameObjectInterface gameObject, GameObjectInterface parent, SimpleMatrix position);

    public Input getInput();

    public RendererInterface getRenderer();

    public void mainloop();

    public void processBehaviours();

    public void processEvents();

    public void updateStates();

    public void processInput();
}
