package DecisionEngine.Core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Tracks ObjectWorldData instances that have had their position updated,
 * so their global position can be recalculated efficiently.
 */
public class PositionUpdate {
    Set<ObjectWorldData> topLevelObjects = new HashSet<ObjectWorldData>();
    Map<ObjectWorldData, Set<ObjectWorldData>> parents = new HashMap<ObjectWorldData, Set<ObjectWorldData>>();

    /**
     * Adds an ObjectWorldData instance to the set, removing any grandchildren if they are present.
     * @param object the ObjectWorldData instance to be added
     */
    public synchronized void add(ObjectWorldData object){
        if (parents.containsKey(object)){
            // At least one of object's grandchildren is already in topLevelObjects
            // therefore none of our grandparents are in topLevelObjects

            Set<ObjectWorldData> children = parents.get(object);

            for (ObjectWorldData child : children){
                // Remove grandchildren from topLevelObjects
                topLevelObjects.remove(child);

                // Remove parents between grandchildren and object
                ObjectWorldData parent = child.parent;
                while (parent != object){
                    // If a parent is already removed, all parents above will
                    // be removed too so move on to the next child
                    if(parents.remove(parent) == null){
                        break;
                    }
                    parent = parent.parent;
                }
            }

            // Remove this object from parents
            parents.remove(object);
            
            // Remove grandchildren from parents above object
            // and add object

            ObjectWorldData parent = object.parent;
            while (parent != null){
                Set<ObjectWorldData> set = parents.get(parent);
                set.removeAll(children);
                set.add(object);
                parent = parent.parent;
            }

            // Add this object to topLevelObjects
            topLevelObjects.add(object);
        }else{
            // None of object's grandchildren are in topLevelObjects, but one of its parents might be

            ObjectWorldData parent = object.parent;
            while (parent != null){
                if (topLevelObjects.contains(parent)){
                    // A parent of this object is already in topLevelObjects, so just return without doing anything
                    return;
                }
                parent = parent.parent;
            }

            // None of our parents or grandchildren are in topLevelObjects, so add object
            topLevelObjects.add(object);

            // then add all of our parents to parents, merging with existing mappings
            parent = object.parent;
            while (parent != null){
                if (parents.containsKey(parent)){
                    // parent already in parents, so add object to set
                    parents.get(parent).add(object);
                }else{
                    // parent not in parents, so add it with object in its set
                    Set<ObjectWorldData> list = new HashSet<ObjectWorldData>();
                    list.add(object);
                    parents.put(parent, list);
                }
                parent = parent.parent;
            }
        }
    }
}
