package DecisionEngine.GameObject;

import DecisionEngine.Render.Renderable;
import DecisionEngine.Shader.Shader;
import DecisionEngine.Texture.Quad;
import DecisionEngine.Texture.TextureInterface;

import static org.lwjgl.opengl.GL33C.*;

public class SpriteObject extends GameObject implements Renderable {
    static final float[] EBO_INDICIES = {
        0, 1, 2,
        1, 2, 3
    };
    Quad objectCoords;
    Quad textureCoords;
    TextureInterface texture;
    int vao;
    public SpriteObject(Quad objectCoords, Quad textureCoords,TextureInterface texture, Shader shader){
        super();
        this.objectCoords = objectCoords;
        this.textureCoords = textureCoords;
        this.texture = texture;
        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        int vbo_verticies = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo_verticies);
        glBufferData(GL_ARRAY_BUFFER, objectCoords.getArray(), GL_STATIC_DRAW);

        int ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, EBO_INDICIES, GL_STATIC_DRAW);
        
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 2*4, 0);
        glEnableVertexAttribArray(0);

        int vbo_texCoords = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo_texCoords);
        glBufferData(GL_ARRAY_BUFFER, textureCoords.getArray(), GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 2*4, 0);
        glEnableVertexAttribArray(1);

    }

    public int getVAO() {
        return vao;
    }

    public int getCount() {
        return EBO_INDICIES.length;
    }
}
