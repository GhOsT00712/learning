package Splitwise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SplitwiseTest {

    @Test
    public void testSplitExpense() {
        List<Payment> payments = List.of(
                new Payment("Alice", 100),
                new Payment("Bob", 50),
                new Payment("Charlie", 30));
        Splitwise splitwise = new Splitwise();
        List<String> result = splitwise.split(payments);
        assertEquals(2, result.size());
        assertEquals("Charlie pays $ 30 to Alice", result.get(0));
        assertEquals("Bob pays $ 10 to Alice", result.get(1));
    }

    @Test
    public void testSplitExpense2() {
        List<Payment> payments = List.of(
                new Payment("Alice", 100),
                new Payment("Bob", 50),
                new Payment("Charlie", 30),
                new Payment("David", 20));
        Splitwise splitwise = new Splitwise();
        List<String> result = splitwise.split(payments);
        assertEquals(2, result.size());
        assertEquals("David pays $ 30 to Alice", result.get(0));
        assertEquals("Charlie pays $ 20 to Alice", result.get(1));
    }

    @Test
    public void testSplitExpense3() {
        List<Payment> payments = List.of(
                new Payment("Alice", 100),
                new Payment("Bob", 90),
                new Payment("Charlie", 30),
                new Payment("David", 20),
                new Payment("Eve", 10));
        Splitwise splitwise = new Splitwise();
        List<String> result = splitwise.split(payments);
        assertEquals(4, result.size());
        assertEquals("Eve pays $ 40 to Alice", result.get(0));
        assertEquals("David pays $ 10 to Alice", result.get(1));
        assertEquals("David pays $ 20 to Bob", result.get(2));
        assertEquals("Charlie pays $ 20 to Bob", result.get(3));
    }

}
