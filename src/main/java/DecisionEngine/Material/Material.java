package DecisionEngine.Material;

import org.ejml.simple.SimpleMatrix;

/**
 * Connects textures to a shader
 */
public interface Material {

    /**
     * Activates the Material's shader and binds the Material's
     * textures to the appropriate texture units
     */
    public void enable(SimpleMatrix cameraTransform, SimpleMatrix globalTransform);

    /**
     * Deactivates the Material's shader and unbinds all used
     * texture units
     */
    public void disable();
}
