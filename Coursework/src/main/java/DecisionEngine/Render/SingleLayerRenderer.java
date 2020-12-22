package DecisionEngine.Render;

import DecisionEngine.LWJGLDelegate.LWJGLInterface;

/**
 * A simple OpenGL renderer that only supports a single render layer
 */
public class SingleLayerRenderer extends OpenGLRenderer {
    public SingleLayerRenderer(LWJGLInterface lwjgl) {
        super(lwjgl);
    }

    /**
     * The renderer's one and only render layer
     */
    RenderLayer layer = new RenderLayer();

    @Override
    public void renderAll(){
        for (Renderable object : layer){
            object.render();

            // glUseProgram(object.getShader().getShaderID());
            // glBindVertexArray(object.getVAO());
            // glBindTexture(GL_TEXTURE_2D, object.getTexture());
            // glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
            //glDrawArrays(GL_TRIANGLES, 0, 6);
        }
    }
}
