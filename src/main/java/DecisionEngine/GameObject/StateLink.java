package DecisionEngine.GameObject;

import DecisionEngine.Event.GameEventInterface;

public class StateLink implements StateLinkInterface {
    protected GameEventInterface event;
    protected StateNodeInterface to;
    protected StateNodeInterface from;

    public StateLink(StateNodeInterface to, StateNodeInterface from, GameEventInterface event) throws NullPointerException {
        if (to == null) {
            throw new NullPointerException("to cannot be null");
        }
        if (from == null) {
            throw new NullPointerException("from cannot be null");
        }
        this.to = to;
        this.from = from;
        this.event = event;
    }

    public void run() {
        to.getMap().setActiveNode(to);
    }

    public StateNodeInterface getTo() {
        return to;
    }

    public StateNodeInterface getFrom() {
        return from;
    }

    public GameEventInterface getEvent() {
        return event;
    }
}
