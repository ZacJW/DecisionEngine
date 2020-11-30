package DecisionEngine.GameObject;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

import DecisionEngine.Core.World;
import DecisionEngine.Event.GameEventInterface;

public class GameObject implements Callable<Set<GameEventInterface>> {
    ArrayList<StateMap> maps;
    World world;

    public GameObject() {
        
    }

    public Set<GameEventInterface> call(){
        world.resetEventCheckSet();

        for (StateMap map : this.maps){
            map.activeNode.behaviour();
        }

        return world.getEventCheckSet();
    }
}