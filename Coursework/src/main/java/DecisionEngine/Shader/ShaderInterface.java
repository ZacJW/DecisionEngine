package DecisionEngine.Shader;

import DecisionEngine.Exceptions.ShaderInitialisationException;

public interface ShaderInterface {
    public int getShaderID();
    public void initialise() throws ShaderInitialisationException;
}
