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

    public int getKey() {
        return key;
    }

    public int getScancode() {
        return scancode;
    }

    public int getAction() {
        return action;
    }

    public int getMods() {
        return mods;
    }
}
