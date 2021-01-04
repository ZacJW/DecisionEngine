package DecisionEngine.Utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void containsKeyTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1,2);
        assertTrue("Map doesn't contain key: 1, but it should", map.containsKey(1));
        assertFalse("Map does contain key: 2, but it shouldn't", map.containsKey(2));
    }

    @Test
    public void containsValueTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1,2);
        assertFalse("Map does contain value: 1, but it shouldn't", map.containsValue(1));
        assertTrue("Map doesn't contain value: 2, but it should", map.containsValue(2));
    }

    @Test
    public void getValueTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1,2);
        assertEquals((Integer) 2, map.getValue(1));
    }

    @Test
    public void getKeyTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1,2);
        assertEquals((Integer) 1, map.getKey(2));
    }

    @Test
    public void removeValueTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1,2);
        map.removeByValue(2);
        assertFalse("Map contains value: 2, but it shouldn't", map.containsValue(2));
        assertFalse("Map contains key: 1, but it shouldn't", map.containsKey(1));
    }

    @Test
    public void removeKeyTest(){
        BiMap<Integer, Integer> map = new BiMap<Integer, Integer>();
        map.put(1,2);
        map.removeByKey(1);
        assertFalse("Map contains value: 2, but it shouldn't", map.containsValue(2));
        assertFalse("Map contains key: 1, but it shouldn't", map.containsKey(1));
    }
}
