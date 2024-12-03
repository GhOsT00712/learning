package Caching;

public class DoublyLinkedList {
    private DLLNode head;
    private DLLNode tail;

    public DoublyLinkedList() {
        head = new DLLNode();
        tail = new DLLNode();
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(DLLNode node){
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
        
    }

    private void removeNode(DLLNode node){
        DLLNode left = node.prev;
        DLLNode right = node.next;
        if(left == null && right == null){
            //newNode
            return;
        }
        left.next = right;
        right.prev = left;
    }

    public void moveToHead(DLLNode node){
        removeNode(node);
        addNode(node);
        return;
    }

    public DLLNode removeFromTail(){
        DLLNode node = tail.prev;
        removeNode(node);
        return node;

    }
}
