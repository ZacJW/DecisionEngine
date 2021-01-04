package DecisionEngine.Input;

public class KeyState {
    int key;
    int scancode;
    boolean pressed;
    int mods;
    public KeyState(int key, int scancode, boolean pressed, int mods){
        this.key = key;
        this.scancode = scancode;
        this.pressed = pressed;
        this.mods = mods;
    }
}
