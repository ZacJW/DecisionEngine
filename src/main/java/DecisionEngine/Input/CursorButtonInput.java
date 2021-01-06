package DecisionEngine.Input;

import java.nio.DoubleBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.system.MemoryStack;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;
import DecisionEngine.Listener.ListenPoint;
import DecisionEngine.Listener.ListenedVar;
import DecisionEngine.Utils.Point2D_Double;

public class CursorButtonInput implements GLFWMouseButtonCallbackI {
    Queue<CursorAction> cursorQueue = new ArrayDeque<CursorAction>();
    Map<Integer, ListenedVar<ArrayList<CursorAction>>> cursorMap = new HashMap<Integer, ListenedVar<ArrayList<CursorAction>>>();
    EventCaptureInterface eventCapture;
    LWJGLInterface lwjgl;

    public CursorButtonInput(EventCaptureInterface eventCapture, LWJGLInterface lwjgl){
        this.eventCapture = eventCapture;
        this.lwjgl = lwjgl;
    }


    @Override
    synchronized public void invoke(long window, int button, int action, int mods) {
        try(MemoryStack stack = MemoryStack.stackPush()){
            DoubleBuffer xpos = stack.callocDouble(1);
            DoubleBuffer ypos = stack.callocDouble(1);
            lwjgl.glfwGetCursorPos(window, xpos, ypos);
            cursorQueue.add(new CursorAction(new Point2D_Double(xpos.get(0), ypos.get(0)), button, action, mods));
        }
    }

    synchronized public ListenPoint<ArrayList<CursorAction>> getListenPoint(int button){
        if (!cursorMap.containsKey(button)){
            cursorMap.put(button, new ListenedVar<ArrayList<CursorAction>>(eventCapture, new ArrayList<CursorAction>()));
        }
        return cursorMap.get(button).getListenPoint();
    }

    synchronized public void processButtonInput(){
        for (ListenedVar<ArrayList<CursorAction>> listenedVar : cursorMap.values()){
            listenedVar.get().clear();
        }
        for (CursorAction action : cursorQueue){
            if (cursorMap.containsKey(action.button)){
                ListenedVar<ArrayList<CursorAction>> listenedVar = cursorMap.get(action.button);
                listenedVar.get().add(action);
                listenedVar.trigger();
            }
        }
        cursorQueue.clear();
    }
    
}
