package DecisionEngine.LWJGLDelegate;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
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
}
