package DecisionEngine.Material;

import DecisionEngine.LWJGLDelegate.LWJGLInterface;
import DecisionEngine.Shader.Shader;
import DecisionEngine.Texture.ImageTexture;

import static org.lwjgl.opengl.GL33C.*;

import org.ejml.simple.SimpleMatrix;

/**
 * A Material that uses Shader.imageShader and a single Texture
 */
public class ImageMaterial implements Material {

    protected Shader shader = Shader.getImageShader();
    protected ImageTexture texture;
    protected LWJGLInterface lwjgl;
    public ImageMaterial(LWJGLInterface lwjgl, ImageTexture texture){
        this.texture = texture;
        this.lwjgl = lwjgl;
    }

    @Override
    public void enable(SimpleMatrix cameraTransform, SimpleMatrix globalTransform) {
        lwjgl.glUseProgram(shader.getShaderID());
        lwjgl.glActiveTexture(GL_TEXTURE0);
        lwjgl.glUniform1i(lwjgl.glGetUniformLocation(shader.getShaderID(), "inTexture"), 0);
        lwjgl.glUniformMatrix4fv(shader.getCameraTransformLocation(), true, cameraTransform.getFDRM().data);
        lwjgl.glUniformMatrix4fv(shader.getGlobalTransformLocation(), true, globalTransform.getFDRM().data);
        lwjgl.glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
    }

    @Override
    public void disable() {
        lwjgl.glUseProgram(0);
        lwjgl.glActiveTexture(GL_TEXTURE0);
        lwjgl.glBindTexture(GL_TEXTURE_2D, 0);
    }
    
}
