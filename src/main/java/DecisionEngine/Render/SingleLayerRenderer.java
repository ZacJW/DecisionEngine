package DecisionEngine.Render;

import org.ejml.simple.SimpleMatrix;

import org.lwjgl.opengl.GL33C;

import DecisionEngine.Core.WorldInterface;
import DecisionEngine.GameObject.CameraInterface;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;

/**
 * A simple OpenGL renderer that only supports a single render layer
 */
public class SingleLayerRenderer extends OpenGLRenderer {
    CameraInterface camera;
    WorldInterface world;
    public SingleLayerRenderer(LWJGLInterface lwjgl, CameraInterface camera, WorldInterface world){
        super(lwjgl);
        this.camera = camera;
        this.world = world;
    }

    /**
     * The renderer's one and only render layer
     */
    RenderLayer layer = new RenderLayer();

    @Override
    public void renderAll(){
        SimpleMatrix fullCameraTransform = camera.getCameraTransform().mult(world.getPosition(camera));
        lwjgl.glClear(GL33C.GL_COLOR_BUFFER_BIT | GL33C.GL_DEPTH_BUFFER_BIT);
        for (Renderable object : layer){
            object.render(fullCameraTransform);

            // glUseProgram(object.getShader().getShaderID());
            // glBindVertexArray(object.getVAO());
            // glBindTexture(GL_TEXTURE_2D, object.getTexture());
            // glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
            //glDrawArrays(GL_TRIANGLES, 0, 6);
        }
        lwjgl.glfwSwapBuffers(window);
    }

    public void addObject(Renderable object){
        layer.add(object);
    }

    public void removeObject(Renderable object){
        layer.remove(object);
    }
}
