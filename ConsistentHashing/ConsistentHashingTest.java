package ConsistentHashing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsistentHashingTest {
    @Test
    void testConsistentHashing() {
        ConsistentHashing consistentHashing = new ConsistentHashing();
        consistentHashing.addNode("Node1");
        consistentHashing.addNode("Node2");
        consistentHashing.addNode("Node3");

        String key1 = "Key1";
        String key2 = "Key2";
        String key3 = "Key3";

        int nodeForKey1 = consistentHashing.getNode(key1);
        int nodeForKey2 = consistentHashing.getNode(key2);
        int nodeForKey3 = consistentHashing.getNode(key3);

        assertNotNull(nodeForKey1);
        assertNotNull(nodeForKey2);
        assertNotNull(nodeForKey3);

        assertEquals(nodeForKey1, consistentHashing.getNode(key1));
        assertEquals(nodeForKey2, consistentHashing.getNode(key2));
        assertEquals(nodeForKey3, consistentHashing.getNode(key3));

        consistentHashing.removeNode("Node2");

        int newNodeForKey1 = consistentHashing.getNode(key1);
        int newNodeForKey2 = consistentHashing.getNode(key2);
        int newNodeForKey3 = consistentHashing.getNode(key3);

        assertNotEquals(-1, newNodeForKey1);
        assertNotEquals(-1, newNodeForKey2);
        assertNotEquals(-1, newNodeForKey3);
    }
}
