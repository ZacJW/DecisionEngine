package DecisionEngine.Shader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Scanner;

import org.lwjgl.system.MemoryStack;

import DecisionEngine.Exceptions.ShaderInitialisationException;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;

import static org.lwjgl.opengl.GL33C.*;

/**
 * A helper class for working with OpenGL shaders
 */
public class Shader implements ShaderInterface{
    private static final String imageShaderVertexString = 
        "#version 330 core\n" +
        "layout (location = 0) in vec2 inPos;\n" +
        "layout (location = 1) in vec2 inTexCoord;\n" +
        "uniform mat4 globalTransform;\n" +
        "uniform mat4 cameraTransform;\n" +

        "out vec2 TexCoord;\n" +
        
        "void main()\n" +
        "{\n" +
        //"    gl_Position = cameraTransform * globalTransform * vec4(inPos, 0.0, 1.0);\n" +
        "    gl_Position = vec4(inPos, 0.0, 1.0);\n" +
        "    TexCoord = inTexCoord;\n" +
        "}\n";

    private static final String imageShaderFragmentString = 
        "#version 330 core\n" +
        "out vec4 FragColor;\n" +
        
        "in vec2 TexCoord;\n" +
        
        "uniform sampler2D inTexture;\n" +
        
        "void main()\n" +
        "{\n" +
        "    FragColor = texture(inTexture, TexCoord);\n" +
        "}\n";
    
    /**
     * A simple shader that just maps a single texture onto geometry and renders it shadelessly
     */
    private volatile static Shader imageShader;

    /**
     * The OpenGL ID for the compiled and linked shader program
     */
    int shader;
    LWJGLInterface lwjgl;
    String vertexShaderString;
    String fragmentShaderString;
    boolean initialised = false;

    private volatile static boolean createdShaders = false;

    /**
     * Creates all the built-in shaders. Be sure to call this before calling the
     * get method for any of the built-in shaders.
     * 
     * Note this does not initialise the created shaders, so this can be called
     * from any thread.
     * @param lwjgl An LWJGL middleman object to proxy all OpenGL calls through.
     */
    public synchronized static void createShaders(LWJGLInterface lwjgl){
        if (createdShaders){
            return;
        }
        imageShader = new Shader(lwjgl, imageShaderVertexString, imageShaderFragmentString);
    }

    /**
     * Gets the built-in imageShader that just textures geometry with a single
     * texture and renders it shadelessly.
     * @return the built-in imageShader.
     */
    public static Shader getImageShader(){
        return imageShader;
    }

    /**
     * Creates a Shader from files containing vertex shader source code and fragment shader source code.
     * @param lwjgl An LWJGL middleman object to proxy all OpenGL calls through.
     * @param vertexShaderFile A File object of the vertex shader source file.
     * @param fragmentShaderFile A File object of the fragment shader source file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Shader(LWJGLInterface lwjgl, File vertexShaderFile, File fragmentShaderFile) throws FileNotFoundException, IOException {
        this.lwjgl = lwjgl;
        Scanner vertexShaderReader = new Scanner(vertexShaderFile);
        Scanner fragmentShaderReader = new Scanner(fragmentShaderFile);
        vertexShaderString = "";
        while (vertexShaderReader.hasNextLine()){
            vertexShaderString += vertexShaderReader.nextLine();
        }
        fragmentShaderString = "";
        while (fragmentShaderReader.hasNextLine()){
            fragmentShaderString += fragmentShaderReader.nextLine();
        }

        vertexShaderReader.close();
        fragmentShaderReader.close();
    }

    /**
     * Creates a Shader from Strings containing vertex shader source code and fragment shader source code.
     * @param lwjgl vertex shader source code and fragment shader source code.
     * @param vertexShaderString A String of the vertex shader source code.
     * @param fragmentShaderString A String of the fragment shader source code.
     */
    public Shader(LWJGLInterface lwjgl, String vertexShaderString, String fragmentShaderString){
        this.lwjgl = lwjgl;
        this.vertexShaderString = vertexShaderString;
        this.fragmentShaderString = fragmentShaderString;
    }

    public void initialise() throws ShaderInitialisationException {
        if (initialised){
            return;
        }
        int vertexShader = lwjgl.glCreateShader(GL_VERTEX_SHADER);
        lwjgl.glShaderSource(vertexShader, vertexShaderString);
        lwjgl.glCompileShader(vertexShader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            lwjgl.glGetShaderiv(vertexShader, GL_COMPILE_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new ShaderInitialisationException("Vertex Shader Compilation Error: " + lwjgl.glGetShaderInfoLog(vertexShader));
            }
        }
        
        int fragmentShader = lwjgl.glCreateShader(GL_FRAGMENT_SHADER);
        lwjgl.glShaderSource(fragmentShader, fragmentShaderString);
        lwjgl.glCompileShader(fragmentShader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            lwjgl.glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new ShaderInitialisationException("Fragment Shader Compilation Error: " + lwjgl.glGetShaderInfoLog(fragmentShader));
            }
        }

        shader = lwjgl.glCreateProgram();
        lwjgl.glAttachShader(shader, vertexShader);
        lwjgl.glAttachShader(shader, fragmentShader);
        lwjgl.glLinkProgram(shader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            lwjgl.glGetProgramiv(shader, GL_LINK_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new ShaderInitialisationException("Shader Program Linking Error: " + lwjgl.glGetProgramInfoLog(shader));
            }
        }
        lwjgl.glDeleteShader(vertexShader);
        lwjgl.glDeleteShader(fragmentShader);
        initialised = true;
    }

    /**
     * Returns the OpenGL ID for the compiled and linked shader program
     */
    public int getShaderID(){
        return shader;
    }
}
