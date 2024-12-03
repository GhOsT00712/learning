package Caching;

public class LFU implements IEviction {

    FreqNode head;
    FreqNode tail;

    public LFU() {
        head = new FreqNode(0);
        tail = new FreqNode(0);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public String evict() {
        return head.next.list.removeFromTail().key;

    }

    @Override
    public void accessed(DLLNode node) {

        if (node.freqNode == null) { // new value node
            if (head.next == tail) { // new freq
                FreqNode newNode = new FreqNode(1);
                newNode.prev = head;
                newNode.next = head.next;
                head.next.prev = newNode;
                head.next = newNode;
                newNode.list.addNode(node);
                node.freqNode = newNode;
            } else {
                FreqNode firstNode = head.next;
                firstNode.list.addNode(node);
                node.freqNode = firstNode;
            }

        } else {
            FreqNode newNode = node.freqNode.next;
            if (newNode == tail || ((newNode.freq - 1) != node.freqNode.freq)) { // next freq is null
                newNode = new FreqNode(node.freqNode.freq + 1);
                newNode.next = node.freqNode.next;
                newNode.prev = node.freqNode;
                node.freqNode.next.prev = newNode;
                node.freqNode.next = newNode;
            }

            node.freqNode.list.removeNode(node);
            if (node.freqNode.list.isEmpty()) {
                deleteFreqNode(node.freqNode);
            }
            newNode.list.addNode(node);
            node.freqNode = newNode;

        }

    }

    @Override
    public int getEvictionThreshold() {
        return 3;
    }

    private void deleteFreqNode(FreqNode node) {
        FreqNode prev = node.prev;
        FreqNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

}