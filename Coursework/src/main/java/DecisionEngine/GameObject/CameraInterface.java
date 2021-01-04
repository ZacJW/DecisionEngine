package DecisionEngine.GameObject;

import org.ejml.simple.SimpleMatrix;

public interface CameraInterface extends GameObjectInterface {

    /**
     * Gets the camera transformation matrix to be used in rendering
     * @return camera transformation matrix
     */
    public SimpleMatrix getCameraTransform();
}
