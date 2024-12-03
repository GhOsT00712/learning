package Caching;

public class DLLNode {

    public DLLNode(){

    }
    
    public DLLNode(String key, String value) {
       this.key = key;
       this.value = value;
    }
    String key;
    String value;
    DLLNode prev;
    DLLNode next;
}
