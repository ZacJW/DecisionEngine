package DecisionEngine.Render;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RenderLayer implements Iterable<Renderable> {
    Set<Renderable> objects = new HashSet<Renderable>();

    public RenderLayer() {

    }

    public void add(Renderable object) {
        objects.add(object);
    }

    public void remove(Renderable object) {
        objects.remove(object);
    }

    public Iterator<Renderable> iterator() {
        return objects.iterator();
    }
}
