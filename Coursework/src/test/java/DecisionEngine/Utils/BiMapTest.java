package DecisionEngine.Utils;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.Test;

public class BiMapTest {
    @Test(expected = KeyAlreadyExistsException.class)
    public void keyExclusionTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1, 2);
        map.put(1, 3);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void valueExclusionTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(2, 1);
        map.put(3, 1);
    }
}
