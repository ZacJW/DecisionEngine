package DecisionEngine.GameObject;

import java.util.Collection;

public interface StateNodeInterface {
    StateMapInterface getMap();
    Collection<? extends StateLinkInterface> getLinks();
    void behaviour();
}
