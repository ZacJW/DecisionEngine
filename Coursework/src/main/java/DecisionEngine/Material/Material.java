package DecisionEngine.Material;

/**
 * Connects textures to a shader
 */
public interface Material {

    /**
     * Activates the Material's shader and binds the Material's
     * textures to the appropriate texture units
     */
    public void enable();

    /**
     * Deactivates the Material's shader and unbinds all used
     * texture units
     */
    public void disable();
}
