package DecisionEngine.GameObject;

import java.util.ArrayList;

public class GameObject implements GameObjectInterface {
    ArrayList<StateMapInterface> maps = new ArrayList<StateMapInterface>();

    public GameObject() {

    }

    public ArrayList<StateMapInterface> getStateMaps() {
        return maps;
    }

    public void run() {
        for (StateMapInterface map : maps) {
            map.getActiveNode().behaviour();
        }
    }

    @Override
    public void initialise() {
        
    }
}