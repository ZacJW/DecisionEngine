package DecisionEngine.Shader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Scanner;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL33C.*;

public class Shader {
    private static final String imageShaderVertexString = 
        "#version 330 core\n" +
        "layout (location = 0) in vec3 inPos;\n" +
        "layout (location = 1) in vec2 inTexCoord;\n" +
        "layout (location = 2) uniform mat4 globalTransform;\n" +
        "layout (location = 3) uniform mat4 cameraTransform;\n" +

        "out vec2 TexCoord;\n" +
        
        "void main()\n" +
        "{\n" +
        "    gl_Position = cameraTransform * globalTransform * vec4(inPos, 1.0);\n" +
        "    TexCoord = inTexCoord;\n" +
        "}\n";

    private static final String imageShaderFragmentString = 
        "#version 330 core\n" +
        "out vec4 FragColor;\n" +
        
        "in vec2 TexCoord;\n" +
        
        "uniform sampler2D uniTexture;\n" +
        
        "void main()\n" +
        "{\n" +
        "    FragColor = texture(uniTexture, TexCoord);\n" +
        "}\n";
    
    public static final Shader imageShader = new Shader(imageShaderVertexString, imageShaderFragmentString);


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
}
