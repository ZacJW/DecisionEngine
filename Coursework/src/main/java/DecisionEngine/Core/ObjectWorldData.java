package DecisionEngine.Core;

import java.util.HashSet;
import java.util.Set;

import org.ejml.simple.SimpleMatrix;

import DecisionEngine.GameObject.GameObjectInterface;

class ObjectWorldData {
    private static final double[][] defaultPosition = {
        {0.0f, 0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f, 0.0f},
        {0.0f, 0.0f, 0.0f, 1.0f},
    };
    GameObjectInterface parent;
    Set<GameObjectInterface> children = new HashSet<GameObjectInterface>();
    SimpleMatrix position;

    ObjectWorldData(){
        position = new SimpleMatrix(defaultPosition);
    }

    ObjectWorldData(SimpleMatrix position){
        if (position.numRows() != 4 || position.numCols() != 4){
            throw new RuntimeException("Position matrix must be 4 rows and 4 columns, not "
                                       + position.numRows() + " rows and " + position.numCols() + " columns.");
        }
        this.position = position;
    }
    
    void setParent(GameObjectInterface parent){
        this.parent = parent;
    }

    void addChild(GameObjectInterface child){
        children.add(child);
    }

    void removeChild(GameObjectInterface child){
        children.remove(child);
    }

    void clearChildren(){
        children.clear();
    }
}
