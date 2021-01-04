package DecisionEngine.Listener;

import DecisionEngine.Core.EventCaptureInterface;

public class ListenedVar<T> {
    EventCaptureInterface eventCapture;
    ListenPoint<T> listenPoint;
    T var;

    public ListenedVar(EventCaptureInterface eventCapture, T var) {
        this.eventCapture = eventCapture;
        this.var = var;
        listenPoint = new ListenPoint<T>(this);
    }

    public T get() {
        return var;
    }

    public void set(T var){
        this.var = var;
        trigger();
    }

    public void trigger(){
        eventCapture.add(listenPoint.listeners);
    }

    public ListenPoint<T> getListenPoint(){
        return listenPoint;
    }


}
