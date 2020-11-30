package DecisionEngine.Listener;

import DecisionEngine.Core.EventCaptureInterface;

public class ListenedVar<T> {
    EventCaptureInterface world;
    ListenPoint<T> listenPoint;
    T var;

    public ListenedVar(EventCaptureInterface world, T var) {
        this.world = world;
        this.var = var;
        listenPoint = new ListenPoint<T>(this);
    }

    public T get() {
        return var;
    }

    public void set(T var){
        this.var = var;
        world.registerEventChecks(listenPoint.listeners);
    }

    public ListenPoint<T> getListenPoint(){
        return listenPoint;
    }


}
