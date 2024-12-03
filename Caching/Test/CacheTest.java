package Caching.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Caching.ICache;
import Caching.IEviction;
import Caching.InMemoryCache;
import Caching.LFU;
import Caching.LRU;
import Caching.Storage;

public class CacheTest {
    @Test
    public void TestLRU() {
        IEviction evictionPolicy = new LRU(3);
        Storage storage = new Storage(evictionPolicy);
        ICache cache = new InMemoryCache(storage);

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        String val1 = cache.get("key1");
        String val2 = cache.get("key2");
        String val3 = cache.get("key3");

        assertEquals("value1", val1);
        assertEquals("value2", val2);
        assertEquals("value3", val3);

        cache.put("key4", "value4");

        String val4 = cache.get("key4");

        assertEquals("value4", val4);
        assertEquals(3, storage.size());
    }

    @Test
    public void TestLFU() {
        IEviction evictionPolicy = new LFU();
        Storage storage = new Storage(evictionPolicy);
        ICache cache = new InMemoryCache(storage);

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        String val1 = cache.get("key1");
        String val2 = cache.get("key1");
        String val3 = cache.get("key2");

        assertEquals("value1", val1);
        assertEquals("value1", val2);
        assertEquals("value2", val3);

        cache.put("key4", "value4");

        String val4 = cache.get("key4");

        assertEquals("value4", val4);
        assertEquals(3, storage.size());

        String valEmpty = cache.get("key3");

        assertEquals("", valEmpty);
    }
}
