package DecisionEngine.Input;

import java.util.ArrayList;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;
import DecisionEngine.Listener.ListenPoint;
import DecisionEngine.Utils.Point2D_Double;

public class Input {
    LWJGLInterface lwjgl;
    long window;
    KeyInput keyInput;
    CursorPositionInput cursorPositionInput;
    CursorButtonInput cursorButtonInput;

    public Input(EventCaptureInterface eventCapture, LWJGLInterface lwjgl, long window){
        this.lwjgl = lwjgl;
        this.window = window;
        keyInput = new KeyInput(eventCapture);
        cursorPositionInput = new CursorPositionInput(eventCapture);
        cursorButtonInput = new CursorButtonInput(eventCapture, lwjgl);
        lwjgl.glfwSetKeyCallback(window, keyInput);
        lwjgl.glfwSetCursorPosCallback(window, cursorPositionInput);
        lwjgl.glfwSetMouseButtonCallback(window, cursorButtonInput);
    }

    public ListenPoint<ArrayList<KeyAction>> getKeyListenPoint(int scancode){
        return keyInput.getListenPoint(scancode);
    }

    public ListenPoint<ArrayList<Point2D_Double>> getCursorPositionListenPoint(){
        return cursorPositionInput.getListenPoint();
    }

    public ListenPoint<ArrayList<CursorAction>> getCursorButtonListenPoint(int button){
        return cursorButtonInput.getListenPoint(button);
    }

    public void processInput(){
        keyInput.processKeyInput();
        cursorPositionInput.processPositionInput();
        cursorButtonInput.processButtonInput();
    }

    public KeyState getKeyState(int scancode){
        return keyInput.getKeyState(scancode);
    }

}
