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
     * 
     * @return the OpenGL location of the camera transformation matrix for use in the vertex shader.
     */
    public int getCameraTransformLocation();

    /**
     * 
     * @return the OpenGL location of the global transformation matrix for use in the vertex shader.
     */
    public int getGlobalTransformLocation();

    /**
     * 
     * @return the OpenGL location of the texture sampler for use in the fragment shader.
     */
    public int getTextureLocation();

    /**
     * Initialises the shader program for use by materials.
     * 
     * All OpenGL calls should be in this method to be called only by the main thread.
     * @throws ShaderInitialisationException
     */
    public void initialise() throws ShaderInitialisationException;
}
