package DecisionEngine.Input;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.Listener.ListenPoint;
import DecisionEngine.Listener.ListenedVar;
import DecisionEngine.Utils.Point2D_Double;

public class CursorPositionInput implements GLFWCursorPosCallbackI {
    Queue<Point2D_Double> cursorQueue = new ArrayDeque<Point2D_Double>();
    Point2D_Double point;
    ListenedVar<ArrayList<Point2D_Double>> cursorPosition;

    public CursorPositionInput(EventCaptureInterface eventCapture){
        cursorPosition = new ListenedVar<ArrayList<Point2D_Double>>(eventCapture, new ArrayList<Point2D_Double>());
    }

    @Override
    synchronized public void invoke(long window, double xpos, double ypos) {
        point = new Point2D_Double(xpos, ypos);
        cursorQueue.add(new Point2D_Double(xpos, ypos));
    }

    synchronized Point2D_Double getPoint(){
        return point;
    }

    synchronized public ListenPoint<ArrayList<Point2D_Double>> getListenPoint(){
        return cursorPosition.getListenPoint();
    }

    synchronized public void processPositionInput(){
        ArrayList<Point2D_Double> list = cursorPosition.get();
        list.clear();
        for (Point2D_Double point : cursorQueue){
            list.add(point);
        }
        cursorPosition.trigger();
        cursorQueue.clear();
    }
    
}
