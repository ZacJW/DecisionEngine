package DecisionEngine.Core;

import org.ejml.simple.SimpleMatrix;

import DecisionEngine.GameObject.GameObjectInterface;

public interface WorldInterface {

    public SimpleMatrix getPosition(GameObjectInterface gameObject);

    public void mainloop();

    public void processBehaviours();

    public void processEvents();

    public void updateStates();
}
