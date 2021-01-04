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

    Shader shader = Shader.getImageShader();
    ImageTexture texture;
    LWJGLInterface lwjgl;
    public ImageMaterial(LWJGLInterface lwjgl, ImageTexture texture){
        this.texture = texture;
        this.lwjgl = lwjgl;
    }

    @Override
    public void enable(SimpleMatrix cameraTransform, SimpleMatrix globalTransform) {
        lwjgl.glUseProgram(shader.getShaderID());
        lwjgl.glActiveTexture(0);
        lwjgl.glUniform1i(lwjgl.glGetUniformLocation(shader.getShaderID(), "inTexture"), 0);
        lwjgl.glUniformMatrix4fv(shader.getCameraTransformLocation(), false, cameraTransform.getFDRM().data);
        lwjgl.glUniformMatrix4fv(shader.getGlobalTransformLocation(), false, globalTransform.getFDRM().data);
        lwjgl.glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
    }

    @Override
    public void disable() {
        lwjgl.glUseProgram(0);
        lwjgl.glActiveTexture(0);
        lwjgl.glBindTexture(GL_TEXTURE_2D, 0);
    }
    
}
