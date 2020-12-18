package DecisionEngine.Shader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Scanner;

import org.lwjgl.system.MemoryStack;

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
    public static final Shader imageShader = new Shader(imageShaderVertexString, imageShaderFragmentString);

    /**
     * The OpenGL ID for the compiled and linked shader program
     */
    int shader;
    public Shader(File vertexShaderFile, File fragmentShaderFile) throws FileNotFoundException, IOException {
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

    public Shader(String vertexShaderString, String fragmentShaderString){
        _Shader(vertexShaderString, fragmentShaderString);
    }

    private void _Shader(String vertexShaderString, String fragmentShaderString){
        
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderString);
        glCompileShader(vertexShader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            glGetShaderiv(vertexShader, GL_COMPILE_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new RuntimeException(glGetShaderInfoLog(vertexShader));
            }
        }
        
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderString);
        glCompileShader(fragmentShader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new RuntimeException(glGetShaderInfoLog(fragmentShader));
            }
        }

        shader = glCreateProgram();
        glAttachShader(shader, vertexShader);
        glAttachShader(shader, fragmentShader);
        glLinkProgram(shader);
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer status = stack.callocInt(1);
            glGetProgramiv(shader, GL_LINK_STATUS, status);
            if (status.get(0) != GL_TRUE){
                throw new RuntimeException(glGetProgramInfoLog(shader));
            }
        }
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }

    /**
     * Returns the OpenGL ID for the compiled and linked shader program
     */
    public int getShaderID(){
        return shader;
    }
}
