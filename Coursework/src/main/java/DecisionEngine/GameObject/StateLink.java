package DecisionEngine.GameObject;

import DecisionEngine.Event.BaseGameEvent;

public class StateLink {
    BaseGameEvent event;
    StateNode to;
    StateNode from;

    public StateLink(StateNode to, StateNode from, BaseGameEvent event) throws NullPointerException {
        if (to == null){
            throw new NullPointerException("to cannot be null");
        }
        if (from == null){
            throw new NullPointerException("from cannot be null");
        }
        this.to = to;
        this.from = from;
        this.event = event;
    }
}
