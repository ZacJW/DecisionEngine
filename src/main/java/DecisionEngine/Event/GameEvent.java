package DecisionEngine.Event;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import DecisionEngine.Core.World;
import DecisionEngine.GameObject.StateLinkInterface;

public abstract class GameEvent implements GameEventInterface {
    protected Set<StateLinkInterface> links = new HashSet<StateLinkInterface>();
    protected Set<GameEventInterface> listeners = new HashSet<GameEventInterface>();
    protected World world;

    public GameEvent(World world){
        this.world = world;
    }
    
    public void run() {
        for (StateLinkInterface link : links){
            world.addStateUpdate(link.getFrom(), link);
        }

    }

    public Collection<? extends StateLinkInterface> getLinks() {
        return links;
    }

    public void addLink(StateLinkInterface link){
        links.add(link);
    }

    public void removeLink(StateLinkInterface link) {
        links.remove(link);

    }

    public Collection<? extends GameEventInterface> getListeners() {
        return listeners;
    }

    public abstract boolean check();

    public void trigger() {
        for (StateLinkInterface link : links){
            world.addStateUpdate(link.getFrom(), link);
        }
    }
    
}
