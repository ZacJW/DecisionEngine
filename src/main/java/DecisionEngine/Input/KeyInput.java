package DecisionEngine.Input;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFW;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.Listener.ListenPoint;
import DecisionEngine.Listener.ListenedVar;

public class KeyInput implements GLFWKeyCallbackI {
    Queue<KeyAction> keyQueue = new ArrayDeque<KeyAction>();
    Map<Integer, ListenedVar<ArrayList<KeyAction>>> keyActionMap = new HashMap<Integer , ListenedVar<ArrayList<KeyAction>>>();
    Map<Integer, KeyState> keyStateMap = new HashMap<Integer, KeyState>();
    EventCaptureInterface eventCapture;
    
    public KeyInput(EventCaptureInterface eventCapture){
        this.eventCapture = eventCapture;
    }

    @Override
    synchronized public void invoke(long window, int key, int scancode, int action, int mods) {
        keyQueue.add(new KeyAction(key, scancode, action, mods));
        if (action != GLFW.GLFW_REPEAT){
            keyStateMap.put(scancode, new KeyState(key, scancode, (action != GLFW.GLFW_RELEASE), mods));
        }
    }
    
    synchronized public Collection<KeyAction> pullAll(){
        Queue<KeyAction> actions = keyQueue;
        keyQueue = new ArrayDeque<KeyAction>();
        return actions;
    }

    synchronized public ListenPoint<ArrayList<KeyAction>> getListenPoint(int scancode){
        if (!keyActionMap.containsKey(scancode)){
            keyActionMap.put(scancode, new ListenedVar<ArrayList<KeyAction>>(eventCapture, new ArrayList<KeyAction>()));
        }
        return keyActionMap.get(scancode).getListenPoint();
    }

    synchronized public KeyState getKeyState(int scancode){
        KeyState state = keyStateMap.get(scancode);
        if (state == null){
            state = new KeyState(0, scancode, false, 0);
        }
        return state;
    }

    synchronized public void processKeyInput(){
        for (ListenedVar<ArrayList<KeyAction>> listenedVar : keyActionMap.values()){
            listenedVar.get().clear();
        }
        for (KeyAction action : keyQueue){
            if (keyActionMap.containsKey(action.scancode)){
                ListenedVar<ArrayList<KeyAction>> listenedVar = keyActionMap.get(action.scancode);
                listenedVar.get().add(action);
                listenedVar.trigger();
            }
        }
        keyQueue.clear();
    }
}
