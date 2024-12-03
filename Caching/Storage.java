package Caching;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<String, DLLNode> map;
    private IEviction evictionPolicy;

    public Storage(IEviction evictionPolicy) {
        map = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            return "";
        }
        var valNode = map.get(key);
        evictionPolicy.accessed(valNode);
        return valNode.value;
    }

    public void put(String key, String value) {
        if (map.containsKey(key)) {
            var valNode = map.get(key);
            valNode.value = value;
            evictionPolicy.accessed(valNode);
            map.put(key, valNode);
            return;
        } else {
            var newNode = new DLLNode(key, value);
            evictionPolicy.accessed(newNode);
            map.put(key, newNode);
            if (map.size() > evictionPolicy.getEvictionThreshold()) {
                String evictedKey = evictionPolicy.evict();
                map.remove(evictedKey);
            }
            return;
        }
    }

    public int size(){
        return map.size();
    }

}
