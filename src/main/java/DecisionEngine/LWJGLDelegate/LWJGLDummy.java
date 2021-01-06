package DecisionEngine.LWJGLDelegate;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GLCapabilities;

public class LWJGLDummy implements LWJGLInterface {

    @Override
    public GLFWErrorCallback GLFWErrorCallbackCreatePrint(PrintStream stream) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GLFWErrorCallback glfwSetErrorCallback(GLFWErrorCallbackI cbfun) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean glfwInit() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void glfwDefaultWindowHints() {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwWindowHint(int hint, int value) {
        // TODO Auto-generated method stub

    }

    @Override
    public long glfwCreateWindow(int width, int height, CharSequence title, long monitor, long share) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glfwGetWindowSize(long window, IntBuffer width, IntBuffer height) {
        // TODO Auto-generated method stub

    }

    @Override
    public GLFWVidMode glfwGetVideMode(long monitor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long glfwGetPrimaryMonitor() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glfwMakeContextCurrent(long window) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwSwapInterval(int interval) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwShowWindow(long window) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwFreeCallbacks(long window) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwDestroyWindow(long window) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwTerminate() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean glfwWindowShouldClose(long window) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void glfwPollEvents() {
        // TODO Auto-generated method stub

    }

    @Override
    public void glfwSwapBuffers(long window) {
        // TODO Auto-generated method stub

    }

    @Override
    public GLFWVidMode glfwGetVideoMode(long monitor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GLCapabilities GLCreateCapabilities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void glClear(int mask) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glViewport(int x, int y, int w, int h) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glUseProgram(int program) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glActiveTexture(int texture) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glUniform1i(int location, int v0) {
        // TODO Auto-generated method stub

    }

    @Override
    public int glGetUniformLocation(int program, CharSequence name) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glBindTexture(int target, int texture) {
        // TODO Auto-generated method stub

    }

    @Override
    public int glGenTextures() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glTexParameteri(int target, int pname, int param) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
            int type, ByteBuffer pixels) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glDeleteTextures(int texture) {
        // TODO Auto-generated method stub

    }

    @Override
    public int glCreateShader(int type) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glShaderSource(int shader, CharSequence string) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glCompileShader(int shader) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        // TODO Auto-generated method stub

    }

    @Override
    public String glGetShaderInfoLog(int shader) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int glCreateProgram() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glAttachShader(int program, int shader) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glLinkProgram(int program) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        // TODO Auto-generated method stub

    }

    @Override
    public String glGetProgramInfoLog(int program) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void glDeleteShader(int shader) {
        // TODO Auto-generated method stub

    }

    @Override
    public int glGenVertexArrays() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glBindVertexArray(int array) {
        // TODO Auto-generated method stub

    }

    @Override
    public int glGenBuffers() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void glBindBuffer(int target, int buffer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glBufferData(int target, FloatBuffer data, int usage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glBufferData(int target, ByteBuffer data, int usage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glEnableVertexAttribArray(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glDrawElements(int mode, int count, int type, long indicies) {
        // TODO Auto-generated method stub

    }

    @Override
    public void glUniformMatrix4fv(int location, boolean transpose, float[] value) {
        // TODO Auto-generated method stub

    }

    @Override
    public GLFWKeyCallback glfwSetKeyCallback(long window, GLFWKeyCallbackI cbfun) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GLFWCursorPosCallback glfwSetCursorPosCallback(long window, GLFWCursorPosCallbackI cbfun) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GLFWMouseButtonCallback glfwSetMouseButtonCallback(long window, GLFWMouseButtonCallbackI cbfun) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void glfwGetCursorPos(long window, DoubleBuffer xpos, DoubleBuffer ypos) {
        // TODO Auto-generated method stub

    }
    
}
