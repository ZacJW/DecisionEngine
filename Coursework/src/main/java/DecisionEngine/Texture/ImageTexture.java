package DecisionEngine.Texture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import static org.lwjgl.opengl.GL33C.*;
import org.lwjgl.stb.*;
import org.lwjgl.system.MemoryStack;

import DecisionEngine.LWJGLDelegate.LWJGLInterface;

public class ImageTexture implements TextureInterface {
    int texture;
    LWJGLInterface lwjgl;
    public ImageTexture(LWJGLInterface lwjgl, String path, int min_filter, int mag_filter) throws FileNotFoundException, IOException{
        this.lwjgl = lwjgl;
        File imageFile = new File(path);
        FileInputStream imageFileStream = new FileInputStream(imageFile);
        FileChannel imageFileChannel = imageFileStream.getChannel();
        ByteBuffer image;
        try (MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer width = stack.callocInt(1);
            IntBuffer height = stack.callocInt(1);
            IntBuffer components = stack.callocInt(1);
            STBImage.stbi_set_flip_vertically_on_load(true);
            image = STBImage.stbi_load_from_memory(imageFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, imageFileChannel.size()), width, height, components, 4);
            texture = lwjgl.glGenTextures();
            lwjgl.glBindTexture(GL_TEXTURE_2D, texture);
            lwjgl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, min_filter);
            lwjgl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, mag_filter);
            lwjgl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            STBImage.stbi_image_free(image);
        }

        imageFileChannel.close();
        imageFileStream.close();
    }

    public int getTextureID(){
        return texture;
    }

    protected void finalize(){
        lwjgl.glDeleteTextures(texture);
    }
}
