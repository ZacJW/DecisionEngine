package DecisionEngine.GameObject;

import DecisionEngine.Material.Material;
import DecisionEngine.Render.Renderable;
import DecisionEngine.Texture.Quad;

import static org.lwjgl.opengl.GL33C.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

public class SpriteObject extends GameObject implements Renderable {
    static final byte[] EBO_INDICIES = {
        0, 1, 2,
        1, 2, 3
    };
    static final ByteBuffer EBO = BufferUtils.createByteBuffer(6).put(EBO_INDICIES);
    Quad objectCoords;
    Quad textureCoords;
    int vao;
    Material material;
    public SpriteObject(Quad objectCoords, Quad textureCoords, Material material){
        super();
        this.objectCoords = objectCoords;
        this.textureCoords = textureCoords;
        this.material = material;
        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        int vbo_verticies = glGenBuffers();
        try (MemoryStack stack = MemoryStack.stackPush()){
            FloatBuffer verticiesBuffer = stack.callocFloat(objectCoords.getArray().length);
            verticiesBuffer.put(objectCoords.getArray()).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vbo_verticies);
            glBufferData(GL_ARRAY_BUFFER, verticiesBuffer, GL_STATIC_DRAW);
        }
        

        int ebo = glGenBuffers();
        try (MemoryStack stack = MemoryStack.stackPush()){
            ByteBuffer indicies = stack.calloc(EBO_INDICIES.length);
            indicies.put(EBO_INDICIES).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicies, GL_STATIC_DRAW);
        }
        
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

        int vbo_texCoords = glGenBuffers();
        try (MemoryStack stack = MemoryStack.stackPush()){
            FloatBuffer texCoordsBuffer = stack.callocFloat(textureCoords.getArray().length);
            texCoordsBuffer.put(textureCoords.getArray()).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vbo_texCoords);
            glBufferData(GL_ARRAY_BUFFER, texCoordsBuffer, GL_STATIC_DRAW);
        }
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

    }

    public int getVAO() {
        return vao;
    }

    public ByteBuffer getEBO() {
        return EBO;
    }

    public void render() {
        try {
            material.enable();
            glBindVertexArray(vao);
            glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
        } finally {
            material.disable();
        }
    }
}
