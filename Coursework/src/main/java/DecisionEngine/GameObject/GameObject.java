package DecisionEngine.GameObject;

import java.util.ArrayList;

import DecisionEngine.Core.World;

public class GameObject implements GameObjectInterface {
    ArrayList<StateMapInterface> maps;
    World world;

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
}