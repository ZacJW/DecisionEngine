package DecisionEngine.GameObject;

import DecisionEngine.Core.WorldInterface;
import DecisionEngine.LWJGLDelegate.LWJGLInterface;
import DecisionEngine.Material.Material;
import DecisionEngine.Render.Renderable;
import DecisionEngine.Texture.Quad;

import static org.lwjgl.opengl.GL33C.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.ejml.simple.SimpleMatrix;
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
    LWJGLInterface lwjgl;
    WorldInterface world;
    boolean initialised = false;
    public SpriteObject(LWJGLInterface lwjgl, WorldInterface world, Quad objectCoords, Quad textureCoords, Material material){
        super();
        this.lwjgl = lwjgl;
        this.world = world;
        this.objectCoords = objectCoords;
        this.textureCoords = textureCoords;
        this.material = material;
    }

    @Override
    public void initialise(){
        if (initialised){
            return;
        }
        this.vao = lwjgl.glGenVertexArrays();
        lwjgl.glBindVertexArray(vao);

        int vbo_verticies = lwjgl.glGenBuffers();
        try (MemoryStack stack = MemoryStack.stackPush()){
            FloatBuffer verticiesBuffer = stack.callocFloat(objectCoords.getArray().length);
            verticiesBuffer.put(objectCoords.getArray()).flip();
            lwjgl.glBindBuffer(GL_ARRAY_BUFFER, vbo_verticies);
            lwjgl.glBufferData(GL_ARRAY_BUFFER, verticiesBuffer, GL_STATIC_DRAW);
        }
        

        int ebo = lwjgl.glGenBuffers();
        try (MemoryStack stack = MemoryStack.stackPush()){
            ByteBuffer indicies = stack.calloc(EBO_INDICIES.length);
            indicies.put(EBO_INDICIES).flip();
            lwjgl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
            lwjgl.glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicies, GL_STATIC_DRAW);
        }
        
        lwjgl.glEnableVertexAttribArray(0);
        lwjgl.glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

        int vbo_texCoords = lwjgl.glGenBuffers();
        try (MemoryStack stack = MemoryStack.stackPush()){
            FloatBuffer texCoordsBuffer = stack.callocFloat(textureCoords.getArray().length);
            texCoordsBuffer.put(textureCoords.getArray()).flip();
            lwjgl.glBindBuffer(GL_ARRAY_BUFFER, vbo_texCoords);
            lwjgl.glBufferData(GL_ARRAY_BUFFER, texCoordsBuffer, GL_STATIC_DRAW);
        }
        lwjgl.glEnableVertexAttribArray(1);
        lwjgl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        initialised = true;
    }

    public int getVAO() {
        return vao;
    }

    public ByteBuffer getEBO() {
        return EBO;
    }

    public void render(SimpleMatrix fullCameraTransform) {
        try {
            material.enable(fullCameraTransform, world.getPosition(this).transpose());
            lwjgl.glBindVertexArray(vao);
            lwjgl.glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
        } finally {
            material.disable();
        }
    }
}
