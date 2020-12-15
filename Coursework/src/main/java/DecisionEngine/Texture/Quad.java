package DecisionEngine.Texture;

public class Quad {
    public float[] coords;
    public Quad(float tl_x, float tl_y, float tr_x, float tr_y, float bl_x, float bl_y, float br_x, float br_y){
        coords = new float[8];
        coords[0] = tl_x;
        coords[1] = tl_y;
        coords[2] = tr_x;
        coords[3] = tr_y;
        coords[4] = bl_x;
        coords[5] = bl_y;
        coords[6] = br_x;
        coords[7] = br_y;
    }

    public float[] getArray(){
        return coords;
    }
}
