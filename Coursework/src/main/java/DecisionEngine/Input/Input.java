package DecisionEngine.Input;

import org.lwjgl.glfw.GLFW;

import DecisionEngine.Core.EventCaptureInterface;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;

public class Input {
    LWJGLInterface lwjgl;
    long window;
    KeyInput keyInput;
    CursorPositionInput cursorPositionInput;
    public Input(EventCaptureInterface eventCapture, LWJGLInterface lwjgl, long window){
        this.lwjgl = lwjgl;
        this.window = window;
        this.keyInput = new KeyInput(eventCapture);
        this.cursorPositionInput = new CursorPositionInput();
        GLFW.glfwSetKeyCallback(window, this.keyInput);
    }

}
