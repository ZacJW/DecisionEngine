package DecisionEngine.Material;

import DecisionEngine.Shader.Shader;
import DecisionEngine.Texture.ImageTexture;

import static org.lwjgl.opengl.GL33C.*;

/**
 * A Material that uses Shader.imageShader and a single Texture
 */
public class ImageMaterial implements Material {

    Shader shader = Shader.imageShader;
    ImageTexture texture;

    public ImageMaterial(ImageTexture texture){
        this.texture = texture;
    }

    @Override
    public void enable() {
        glUseProgram(shader.getShaderID());
        glActiveTexture(0);
        glUniform1i(glGetUniformLocation(shader.getShaderID(), "inTexture"), 0);
        glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
    }

    @Override
    public void disable() {
        glUseProgram(0);
        glActiveTexture(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
}
