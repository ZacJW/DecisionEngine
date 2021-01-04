package DecisionEngine.Render;

import org.ejml.simple.SimpleMatrix;

public interface Renderable {
    public void render(SimpleMatrix fullCameraTransform);
}
