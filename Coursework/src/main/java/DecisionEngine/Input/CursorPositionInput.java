package DecisionEngine.Input;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import DecisionEngine.Utils.Point2D_Double;

public class CursorPositionInput implements GLFWCursorPosCallbackI {
    Point2D_Double point;

    @Override
    synchronized public void invoke(long window, double xpos, double ypos) {
        point = new Point2D_Double(xpos, ypos);
    }

    synchronized Point2D_Double getPoint(){
        return point;
    }
    
}
