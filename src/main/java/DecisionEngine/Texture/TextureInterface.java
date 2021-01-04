package DecisionEngine.Texture;

import DecisionEngine.Exceptions.TextureInitialisationException;

public interface TextureInterface {
    public void initialise() throws TextureInitialisationException;
    public int getTextureID();
}
