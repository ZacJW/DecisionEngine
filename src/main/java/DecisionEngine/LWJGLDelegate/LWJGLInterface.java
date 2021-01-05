package DecisionEngine.LWJGLDelegate;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GLCapabilities;

public interface LWJGLInterface {
    public GLFWErrorCallback GLFWErrorCallbackCreatePrint(PrintStream stream);
    public GLFWErrorCallback glfwSetErrorCallback(GLFWErrorCallbackI cbfun);
    public boolean glfwInit();
    public void glfwDefaultWindowHints();
    public void glfwWindowHint(int hint, int value);
    public long glfwCreateWindow(int width, int height, CharSequence title, long monitor, long share);
    public void glfwGetWindowSize(long window, IntBuffer width, IntBuffer height);
    public GLFWVidMode glfwGetVideMode(long monitor);
    public long glfwGetPrimaryMonitor();
    public void glfwMakeContextCurrent(long window);
    public void glfwSwapInterval(int interval);
    public void glfwShowWindow(long window);
    public void glfwFreeCallbacks(long window);
    public void glfwDestroyWindow(long window);
    public void glfwTerminate();
    public boolean glfwWindowShouldClose(long window);
    public void glfwPollEvents();
    public void glfwSwapBuffers(long window);
    public GLFWVidMode glfwGetVideoMode(long monitor);
    public GLFWKeyCallback glfwSetKeyCallback(long window, GLFWKeyCallbackI cbfun);
    public GLFWCursorPosCallback glfwSetCursorPosCallback(long window, GLFWCursorPosCallbackI cbfun);
    
    public GLCapabilities GLCreateCapabilities();
    
    public void glClear(int mask);
    public void glViewport(int x, int y, int w, int h);
    public void glUseProgram(int program);
    public void glActiveTexture(int texture);
    public void glUniform1i(int location, int v0);
    public int glGetUniformLocation(int program, CharSequence name);
    public void glBindTexture(int target, int texture);
    public int glGenTextures();
    public void glTexParameteri(int target, int pname, int param);
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels);
    public void glDeleteTextures(int texture);
    public int glCreateShader(int type);
    public void glShaderSource(int shader, CharSequence string);
    public void glCompileShader(int shader);
    public void glGetShaderiv(int shader, int pname, IntBuffer params);
    public String glGetShaderInfoLog(int shader);
    public int glCreateProgram();
    public void glAttachShader(int program, int shader);
    public void glLinkProgram(int program);
    public void glGetProgramiv(int program, int pname, IntBuffer params);
    public String glGetProgramInfoLog(int program);
    public void glDeleteShader(int shader);
    public int glGenVertexArrays();
    public void glBindVertexArray(int array);
    public int glGenBuffers();
    public void glBindBuffer(int target, int buffer);
    public void glBufferData(int target, FloatBuffer data, int usage);
    public void glBufferData(int target, ByteBuffer data, int usage);
    public void glEnableVertexAttribArray(int index);
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer);
    public void glDrawElements(int mode, int count, int type, long indicies);
    public void glUniformMatrix4fv(int location, boolean transpose, float[] value);
}
