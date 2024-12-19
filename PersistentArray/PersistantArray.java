package PersistentArray;

import java.util.Arrays;
/**
 * A persistent array is an array that preserves the previous versions of the array when it is updated.
 * This is done by creating a new node with the updated array and linking it to the previous node.
 * This way, the previous versions of the array are preserved and can be accessed at any time.
 */
public class PersistantArray {
    private PersistantArrayNode root;
    private int[] latestArrayVersion;
    private int size;

    public PersistantArray(int[] array) {
        this.latestArrayVersion = array;
        this.size = array.length;
        this.root = new PersistantArrayNode(array, 0, array[0], null);
    }

    /**
     * Updates the array with the new value at the given index.
     * @param array The array to be updated
     * @param index The index of the element to be updated
     * @param value The new value of the element
     */
    public void update(int[] array, int index, int value) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        this.latestArrayVersion[index] = value;

        PersistantArrayNode node = new PersistantArrayNode(this.latestArrayVersion, index, value, root);
        root = node;
    }

    /**
     * Gets the value at the given index in the array in a given array version.
     * @param array The array version to get the value from
     * @param index The index of the element to get
     * @return  The value at the given index
     */
    public int get(int[] array, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        PersistantArrayNode node = root;
        while (node != null && !Arrays.equals(array, node.getArray())) {
            node = node.getParent();
        }

        return node != null ? node.getArray()[index] : -1;
    }

}
