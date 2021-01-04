package DecisionEngine.Input;

public class KeyAction {
    int key;
    int scancode;
    int action;
    int mods;
    public KeyAction(int key, int scancode, int action, int mods){
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.mods = mods;
    }
}
