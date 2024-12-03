package Caching;

public class InMemoryCache implements ICache {
    private Storage cache;

    public InMemoryCache(Storage storage) {
        cache = storage;
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
        return;
    }

}
