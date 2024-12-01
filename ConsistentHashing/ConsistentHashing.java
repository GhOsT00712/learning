package ConsistentHashing;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * ConsistentHashing class that implements the consistent hashing algorithm.
 * The class uses SHA-256 to hash the keys and the nodes.
 * The class uses a hash space of 1000.
 */
public class ConsistentHashing {

    private final double keySpace = 1000;
    private final int intKeySpace = (int) keySpace;
    private int numberOfNodes = 1;

    // hashSpace is an array that represents the hash space
    private int[] hashSpace;

    // nodesIdentifiers is an array that stores the identifiers of the nodes
    private int[] nodesIdentifiers;

    public ConsistentHashing() {
        hashSpace = new int[intKeySpace];
        nodesIdentifiers = new int[numberOfNodes];
    }

    /**
     * Returns the index of the node that should store the key.
     * 
     * @param key The key to be stored.
     * @return The index of the node that should store the key, or -1 if there is a
     *         collision.
     */
    public int getNode(String key) {
        int keyIndex = hash(key);
        int nodeIndex = Arrays.binarySearch(nodesIdentifiers, keyIndex);

        if (nodeIndex > 0) {
            return -1; // collision
        }

        if (nodeIndex < 0) {
            nodeIndex = -nodeIndex - 1;
            if (nodeIndex == hashSpace.length) {
                nodeIndex = 0;
            }
        }

        return nodeIndex;
    }

    /**
     * Adds a node to the hash ring.
     * 
     * @param nodeIdentifier The identifier of the node to be added.
     */
    public void addNode(String nodeIdentifier) {
        int nodeIndex = hash(nodeIdentifier);
        if (hashSpace[nodeIndex] == 1) {
            // collision
            return;
        }
        hashSpace[nodeIndex] = 1;
        this.numberOfNodes++;
        int atIndexToInsert = Arrays.binarySearch(nodesIdentifiers, nodeIndex);
        if (atIndexToInsert > 0) {
            return;
        }
        atIndexToInsert = -atIndexToInsert - 1;
        nodesIdentifiers = Arrays.copyOf(nodesIdentifiers, numberOfNodes);
        for (int i = numberOfNodes - 1; i > atIndexToInsert; i--) {
            nodesIdentifiers[i] = nodesIdentifiers[i - 1];
        }
        nodesIdentifiers[atIndexToInsert] = nodeIndex;
    }

    /**
     * Removes a node from the hash ring.
     * 
     * @param nodeIdentifier The identifier of the node to be removed.
     */
    public void removeNode(String nodeIdentifier) {
        int nodeIndex = hash(nodeIdentifier);
        if (hashSpace[nodeIndex] == 0) {
            // node not found
            return;
        }
        hashSpace[nodeIndex] = 0;
        int index = Arrays.binarySearch(nodesIdentifiers, nodeIndex);
        if (index < 0) {
            // node not found
            return;
        }
        for (int i = index; i < numberOfNodes - 1; i++) {
            nodesIdentifiers[i] = nodesIdentifiers[i + 1];
        }
        numberOfNodes--;
        nodesIdentifiers = Arrays.copyOf(nodesIdentifiers, numberOfNodes);
    }

    /**
     * Hashes the given key using SHA-256 and returns an integer value.
     * 
     * @param key The key to be hashed.
     * @return The hashed value as an integer.
     */
    private int hash(String key) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return -1;
        }
        byte[] hashInBytes = md.digest(key.getBytes(StandardCharsets.UTF_8));
        int hashInInt = ByteBuffer.wrap(Arrays.copyOfRange(hashInBytes, 0, 4)).getInt();
        return (hashInInt % intKeySpace + intKeySpace) % intKeySpace;
    }
}
