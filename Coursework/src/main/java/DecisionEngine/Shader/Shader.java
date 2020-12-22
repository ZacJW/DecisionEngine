package DecisionEngine.Shader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Scanner;

import org.lwjgl.system.MemoryStack;

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
    private static Shader imageShader;

    /**
     * The OpenGL ID for the compiled and linked shader program
     */
    int shader;
    LWJGLInterface lwjgl;
    private static boolean initialised = false;
    public static void initialise(LWJGLInterface lwjgl){
        if (initialised){
            return;
        }
        imageShader = new Shader(lwjgl, imageShaderVertexString, imageShaderFragmentString);
    }

    public static Shader getImageShader(){
        return imageShader;
    }

    public Shader(LWJGLInterface lwjgl, File vertexShaderFile, File fragmentShaderFile) throws FileNotFoundException, IOException {
        this.lwjgl = lwjgl;
        Scanner vertexShaderReader = new Scanner(vertexShaderFile);
        Scanner fragmentShaderReader = new Scanner(fragmentShaderFile);
        String vertexShaderString = "";
        while (vertexShaderReader.hasNextLine()){
            vertexShaderString += vertexShaderReader.nextLine();
        }
        String fragmentShaderString = "";
        while (fragmentShaderReader.hasNextLine()){
            fragmentShaderString += fragmentShaderReader.nextLine();
        }

        _Shader(vertexShaderString, fragmentShaderString);

        vertexShaderReader.close();
        fragmentShaderReader.close();
    }

    public Shader(LWJGLInterface lwjgl, String vertexShaderString, String fragmentShaderString){
        this.lwjgl = lwjgl;
        _Shader(vertexShaderString, fragmentShaderString);
    }

    private void _Shader(String vertexShaderString, String fragmentShaderString){
        
        int vertexShader = lwjgl.glCreateShader(GL_VERTEX_SHADER);
        lwjgl.glShaderSource(vertexShader, vertexShaderString);
        lwjgl.glCompileShader(vertexShader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            lwjgl.glGetShaderiv(vertexShader, GL_COMPILE_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new RuntimeException(lwjgl.glGetShaderInfoLog(vertexShader));
            }
        }
        
        int fragmentShader = lwjgl.glCreateShader(GL_FRAGMENT_SHADER);
        lwjgl.glShaderSource(fragmentShader, fragmentShaderString);
        lwjgl.glCompileShader(fragmentShader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            lwjgl.glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new RuntimeException(lwjgl.glGetShaderInfoLog(fragmentShader));
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
                throw new RuntimeException(lwjgl.glGetProgramInfoLog(shader));
            }
        }
        lwjgl.glDeleteShader(vertexShader);
        lwjgl.glDeleteShader(fragmentShader);
    }

    /**
     * Returns the OpenGL ID for the compiled and linked shader program
     */
    public int getShaderID(){
        return shader;
    }
}
