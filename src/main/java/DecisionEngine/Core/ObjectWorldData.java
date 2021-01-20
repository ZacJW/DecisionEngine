package DecisionEngine.Core;

import java.util.HashSet;
import java.util.Set;

import org.ejml.data.FMatrixRMaj;
import org.ejml.data.MatrixType;
import org.ejml.simple.SimpleMatrix;

class ObjectWorldData {
    ObjectWorldData parent;
    Set<ObjectWorldData> children = new HashSet<ObjectWorldData>();
    SimpleMatrix globalPosition = SimpleMatrix.identity(4, FMatrixRMaj.class);
    SimpleMatrix position;

    ObjectWorldData(){
        position = SimpleMatrix.identity(4, FMatrixRMaj.class);
    }

    ObjectWorldData(SimpleMatrix position){
        checkMatrix(position);
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
        checkMatrix(position);
        this.position = position.copy();
    }

    void checkMatrix(SimpleMatrix matrix){
        if (matrix.getType() != MatrixType.FDRM){
            throw new RuntimeException("matrix must wrap FMatrixRMaj");
        }
        if (matrix.numRows() != 4 || matrix.numCols() != 4){
            throw new RuntimeException("Matrix must be 4 rows and 4 columns, not "
                                       + matrix.numRows() + " rows and " + matrix.numCols() + " columns.");
        } 
    }
}
