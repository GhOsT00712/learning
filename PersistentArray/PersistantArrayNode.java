package PersistentArray;

import java.util.Arrays;

public class PersistantArrayNode {
    private int[] array;
    private PersistantArrayNode parent;
    private int index;
    private int value;

    public PersistantArrayNode(int[] array, int index, int value, PersistantArrayNode parent) {
        this.index = index;
        this.value = value;
        this.array = Arrays.copyOf(array, array.length);
        
    }

    public int getArrayHash() {
        return Arrays.hashCode(array);
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public PersistantArrayNode getParent() {
        return parent;
    }

    public int[] getArray() {
        return array;
    }
}
