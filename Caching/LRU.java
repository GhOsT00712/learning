package Caching;

public class LRU implements IEviction {

    DoublyLinkedList dll;

    int evictionThreshold;

    public LRU(int evictionThreshold) {
        this.evictionThreshold = evictionThreshold;
        this.dll = new DoublyLinkedList();
    }

    @Override
    public String evict() {
        return dll.removeFromTail().key;
    }

    public void accessed(DLLNode node) {
        dll.moveToHead(node);
    }

    @Override
    public int getEvictionThreshold() {
        return evictionThreshold;
    }

}
