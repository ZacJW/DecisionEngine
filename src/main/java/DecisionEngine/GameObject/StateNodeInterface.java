package DecisionEngine.GameObject;

import DecisionEngine.Utils.BiMap;

public interface StateNodeInterface {
    StateMapInterface getMap();
    //Collection<? extends StateLinkInterface> getLinks();
    BiMap<Integer, StateLinkInterface> getLinks();
    boolean isActive();
    void behaviour();
}
