package DecisionEngine.LWJGLDelegate;

import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GL33C;
import org.lwjgl.opengl.GL;

public class LWJGLDelegate implements LWJGLInterface {

    public static final LWJGLDelegate lwjgl = new LWJGLDelegate();

    private LWJGLDelegate() {

    }

    @Override
    public GLFWErrorCallback GLFWErrorCallbackCreatePrint(PrintStream stream) {
        return GLFWErrorCallback.createPrint(stream);
    }

    @Override
    public GLFWErrorCallback glfwSetErrorCallback(GLFWErrorCallbackI cbfun) {
        return GLFW.glfwSetErrorCallback(cbfun);
    }

    @Override
    public boolean glfwInit() {
        return GLFW.glfwInit();
    }

    @Override
    public void glfwDefaultWindowHints() {
        GLFW.glfwDefaultWindowHints();
    }

    @Override
    public void glfwWindowHint(int hint, int value) {
        GLFW.glfwWindowHint(hint, value);
    }

    @Override
    public long glfwCreateWindow(int width, int height, CharSequence title, long monitor, long share) {
        return GLFW.glfwCreateWindow(width, height, title, monitor, share);
    }

    @Override
    public void glfwGetWindowSize(long window, IntBuffer width, IntBuffer height) {
        GLFW.glfwGetWindowSize(window, width, height);
    }

    @Override
    public GLFWVidMode glfwGetVideMode(long monitor) {
        return GLFW.glfwGetVideoMode(monitor);
    }

    @Override
    public long glfwGetPrimaryMonitor() {
        return GLFW.glfwGetPrimaryMonitor();
    }

    @Override
    public void glfwMakeContextCurrent(long window) {
        GLFW.glfwMakeContextCurrent(window);
    }

    @Override
    public void glfwSwapInterval(int interval) {
        GLFW.glfwSwapInterval(interval);
    }

    @Override
    public void glfwShowWindow(long window) {
        GLFW.glfwShowWindow(window);
    }

    @Override
    public void glfwFreeCallbacks(long window) {
        Callbacks.glfwFreeCallbacks(window);
    }

    @Override
    public void glfwDestroyWindow(long window) {
        GLFW.glfwDestroyWindow(window);
    }

    @Override
    public void glfwTerminate() {
        GLFW.glfwTerminate();
    }

    @Override
    public boolean glfwWindowShouldClose(long window) {
        return GLFW.glfwWindowShouldClose(window);
    }

    @Override
    public void glfwPollEvents() {
        GLFW.glfwPollEvents();
    }

    @Override
    public void glfwSwapBuffers(long window) {
        GLFW.glfwSwapBuffers(window);
    }

    @Override
    public GLFWVidMode glfwGetVideoMode(long monitor) {
        return GLFW.glfwGetVideoMode(monitor);
    }

    @Override
    public GLCapabilities GLCreateCapabilities() {
        return GL.createCapabilities();
    }

    @Override
    public void glClear(int mask) {
        GL33C.glClear(mask);
    }

    @Override
    public void glViewport(int x, int y, int w, int h) {
        GL33C.glViewport(x, y, w, h);
    }

    @Override
    public void glUseProgram(int program) {
        GL33C.glUseProgram(program);
    }

    @Override
    public void glActiveTexture(int texture) {
        GL33C.glActiveTexture(texture);
    }

    @Override
    public void glUniform1i(int location, int v0) {
        GL33C.glUniform1i(location, v0);
    }

    @Override
    public int glGetUniformLocation(int program, CharSequence name) {
        return GL33C.glGetUniformLocation(program, name);
    }

    @Override
    public void glBindTexture(int target, int texture) {
        GL33C.glBindTexture(target, texture);
    }

    @Override
    public int glGenTextures() {
        return GL33C.glGenTextures();
    }

    @Override
    public void glTexParameteri(int target, int pname, int param) {
        GL33C.glTexParameteri(target, pname, param);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format,
            int type, ByteBuffer pixels) {
        GL33C.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glDeleteTextures(int texture) {
        GL33C.glDeleteTextures(texture);
    }

    @Override
    public int glCreateShader(int type) {
        return GL33C.glCreateShader(type);
    }

    @Override
    public void glShaderSource(int shader, CharSequence string) {
        GL33C.glShaderSource(shader, string);
    }

    @Override
    public void glCompileShader(int shader) {
        GL33C.glCompileShader(shader);
    }

    @Override
    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        GL33C.glGetShaderiv(shader, pname, params);
    }

    @Override
    public String glGetShaderInfoLog(int shader) {
        return GL33C.glGetShaderInfoLog(shader);
    }

    @Override
    public int glCreateProgram() {
        return GL33C.glCreateProgram();
    }

    @Override
    public void glAttachShader(int program, int shader) {
        GL33C.glAttachShader(program, shader);
    }

    @Override
    public void glLinkProgram(int program) {
        GL33C.glLinkProgram(program);
    }

    @Override
    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        GL33C.glGetProgramiv(program, pname, params);
    }

    @Override
    public String glGetProgramInfoLog(int program) {
        return GL33C.glGetProgramInfoLog(program);
    }

    @Override
    public void glDeleteShader(int shader) {
        GL33C.glDeleteShader(shader);
    }

    @Override
    public int glGenVertexArrays() {
        return GL33C.glGenVertexArrays();
    }

    @Override
    public void glBindVertexArray(int array) {
        GL33C.glBindVertexArray(array);
    }

    @Override
    public int glGenBuffers() {
        return GL33C.glGenBuffers();
    }

    @Override
    public void glBindBuffer(int target, int buffer) {
        GL33C.glBindBuffer(target, buffer);
    }

    @Override
    public void glBufferData(int target, FloatBuffer data, int usage) {
        GL33C.glBufferData(target, data, usage);
    }

    @Override
    public void glBufferData(int target, ByteBuffer data, int usage) {
        GL33C.glBufferData(target, data, usage);
    }

    @Override
    public void glEnableVertexAttribArray(int index) {
        GL33C.glEnableVertexAttribArray(index);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        GL33C.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void glDrawElements(int mode, int count, int type, long indices) {
        GL33C.glDrawElements(mode, count, type, indices);
    }

    @Override
    public void glUniformMatrix4fv(int location, boolean transpose, float[] value) {
        GL33C.glUniformMatrix4fv(location, transpose, value);
    }

    @Override
    public GLFWKeyCallback glfwSetKeyCallback(long window, GLFWKeyCallbackI cbfun) {
        return GLFW.glfwSetKeyCallback(window, cbfun);
    }

    @Override
    public GLFWCursorPosCallback glfwSetCursorPosCallback(long window, GLFWCursorPosCallbackI cbfun) {
        return GLFW.glfwSetCursorPosCallback(window, cbfun);
    }
}
