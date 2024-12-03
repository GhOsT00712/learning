package Caching;

public interface ICache {
    String get(String key);
    void put(String key, String value);
}