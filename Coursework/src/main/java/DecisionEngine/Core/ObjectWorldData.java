package DecisionEngine.Core;

import java.util.HashSet;
import java.util.Set;

import org.ejml.simple.SimpleMatrix;

class ObjectWorldData {
    private static final float[][] defaultPosition = {
        {0.0f, 0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f, 1.0f},
    };
    ObjectWorldData parent;
    Set<ObjectWorldData> children = new HashSet<ObjectWorldData>();
    SimpleMatrix globalPosition = new SimpleMatrix(defaultPosition);
    SimpleMatrix position;

    ObjectWorldData(){
        position = new SimpleMatrix(defaultPosition);
    }

    ObjectWorldData(SimpleMatrix position){
        checkMatrixSize(position);
        this.position = position;
    }
    
    void setParent(ObjectWorldData parent){
        this.parent = parent;
    }

    void addChild(ObjectWorldData child){
        children.add(child);
    }

    void removeChild(ObjectWorldData child){
        children.remove(child);
    }

    void clearChildren(){
        children.clear();
    }

    void updatePosition(SimpleMatrix position){
        checkMatrixSize(position);
        this.position = position.copy();
    }

    void checkMatrixSize(SimpleMatrix matrix){
        if (matrix.numRows() != 4 || matrix.numCols() != 4){
            throw new RuntimeException("Matrix must be 4 rows and 4 columns, not "
                                       + matrix.numRows() + " rows and " + matrix.numCols() + " columns.");
        } 
    }
}
