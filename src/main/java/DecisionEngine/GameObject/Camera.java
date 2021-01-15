package DecisionEngine.GameObject;

import org.ejml.simple.SimpleMatrix;

public class Camera extends GameObject implements CameraInterface {
    protected SimpleMatrix cameraTransform;

    public Camera(SimpleMatrix cameraTransform){
        super();
        if (cameraTransform.numRows() != 4 || cameraTransform.numCols() != 4){
            throw new RuntimeException("Matrix must be 4 rows and 4 columns, not "
                                       + cameraTransform.numRows() + " rows and "
                                       + cameraTransform.numCols() + " columns.");
        }
        this.cameraTransform = cameraTransform;
    }

    @Override
    public SimpleMatrix getCameraTransform() {
        return cameraTransform.copy();
    }
    
}
