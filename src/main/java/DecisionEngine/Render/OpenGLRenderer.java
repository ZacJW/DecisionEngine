package DecisionEngine.Render;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryStack;

import DecisionEngine.LWJGLDelegate.LWJGLInterface;

/**
 * An OpenGL render with support for multiple render layers
 */
public class OpenGLRenderer implements RendererInterface{
    protected long window;
    boolean initialised = false;
    protected int windowWidth;
    protected int windowHeight;
    protected LWJGLInterface lwjgl;
    protected GLCapabilities glCapabilities;

    public OpenGLRenderer(LWJGLInterface lwjgl){
        this.lwjgl = lwjgl;
    }
    
    public void initialise() {
        if (initialised){
            return;
        }
        lwjgl.GLFWErrorCallbackCreatePrint(System.err);
        if( !lwjgl.glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        lwjgl.glfwDefaultWindowHints();
        lwjgl.glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        lwjgl.glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = lwjgl.glfwCreateWindow(1280, 720, "", NULL, NULL);
        if (window == NULL){
            throw new RuntimeException("GLFW Window creation failed");
        }

        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            lwjgl.glfwGetWindowSize(window, width, height);
            GLFWVidMode vidmode = lwjgl.glfwGetVideoMode(lwjgl.glfwGetPrimaryMonitor());
            windowWidth = width.get(0);
            windowHeight = height.get(0);
            // glfwSetWindowPos(window, 
            //                  vidmode.width() - windowWidth / 2,
            //                  vidmode.height() - windowHeight / 2);
            lwjgl.glfwMakeContextCurrent(window);
            lwjgl.glfwSwapInterval(1);
            lwjgl.glfwShowWindow(window);
            glCapabilities = lwjgl.GLCreateCapabilities();
        }

        initialised = true;
    }

    public void renderAll() {
        // TODO Auto-generated method stub

    }

    public void terminate() {
        lwjgl.glfwFreeCallbacks(window);
        lwjgl.glfwDestroyWindow(window);
        lwjgl.glfwTerminate();
        lwjgl.glfwSetErrorCallback(null);
        initialised = false;
    }

    @Override
    public boolean shouldTerminate() {
        return lwjgl.glfwWindowShouldClose(window);
    }

    @Override
    public void pollEvents() {
        lwjgl.glfwPollEvents();
    }

    @Override
    public long getWindow() {
        return window;
    }
    
}
