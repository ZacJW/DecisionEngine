package DecisionEngine.Utils;

import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

public class BiMap<K, V> {
    Map<K,V> keyToValue;
    Map<V,K> valueToKey;
    public BiMap(){
        keyToValue = new HashMap<K, V>();
        valueToKey = new HashMap<V, K>();
    }
    public void put(K key, V value) throws KeyAlreadyExistsException{
        if (keyToValue.containsKey(key)){
            throw new KeyAlreadyExistsException("Key: " + key.toString() + " already exists in BiMap");
        }
        if (valueToKey.containsKey(value)){
            throw new KeyAlreadyExistsException("Value: " + value.toString() + " already exists in BiMap");
        }
        keyToValue.put(key, value);
        valueToKey.put(value, key);
    }
    public V removeByKey(K key){
        V v = keyToValue.remove(key);
        valueToKey.remove(v);
        return v;
    }
    public K removeByValue(V value){
        K k = valueToKey.remove(value);
        keyToValue.remove(k);
        return k;
    }
    public V getValue(K key){
        return keyToValue.get(key);
    }
    public K getKey(V value){
        return valueToKey.get(value);
    }
    public boolean containsKey(K key){
        return keyToValue.containsKey(key);
    }
    public boolean containsValue(V value){
        return valueToKey.containsKey(value);
    }
}
