package DecisionEngine.Input;

import DecisionEngine.Utils.Point2D_Double;

public class CursorAction {
    Point2D_Double point;
    int button;
    int action;
    int mods;
    public CursorAction(Point2D_Double point, int button, int action, int mods){
        this.point = point;
        this.button = button;
        this.action = action;
        this.mods = mods;
    }
}
