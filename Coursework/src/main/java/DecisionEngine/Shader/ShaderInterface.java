package DecisionEngine.Shader;

import DecisionEngine.Exceptions.ShaderInitialisationException;

/**
 * The interface all shader program objects must implement.
 */
public interface ShaderInterface {

    /**
     * Gets the OpenGL shader program object ID
     * @return shader program ID
     */
    public int getShaderID();

    /**
     * Initialises the shader program for use by materials.
     * 
     * All OpenGL calls should be in this method to be called only by the main thread.
     * @throws ShaderInitialisationException
     */
    public void initialise() throws ShaderInitialisationException;
}
