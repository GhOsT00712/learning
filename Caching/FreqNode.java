package Caching;

public class FreqNode {
    int freq;
    FreqNode next;
    FreqNode prev;
    DoublyLinkedList list;

    public FreqNode(int freq) {
        this.freq = freq;
        list = new DoublyLinkedList();
    }

}
