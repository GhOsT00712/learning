package Caching;

public interface IEviction {
    String evict();
    void accessed(DLLNode node);
    int getEvictionThreshold();
}
