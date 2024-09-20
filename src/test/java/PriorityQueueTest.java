import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.PriorityQueue;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @BeforeEach
    public void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    public void testIsEmptyOnNewQueue() {
        assertTrue(pq.isEmpty());
    }

    @Test
    public void testAddAndPeek() {
        pq.add(10);
        pq.add(20);
        pq.add(5);

        assertEquals(5, pq.peek());
        assertFalse(pq.isEmpty());
    }

    @Test
    public void testAddAndPoll() {
        pq.add(10);
        pq.add(20);
        pq.add(5);

        assertEquals(5, pq.poll());
        assertEquals(10, pq.peek());
    }

    @Test
    public void testAddWithComparator() {
        PriorityQueue<Integer> reversePq = new PriorityQueue<>(Comparator.reverseOrder());

        reversePq.add(10);
        reversePq.add(20);
        reversePq.add(5);

        assertEquals(20, reversePq.peek());
    }

    @Test
    public void testRemoveElement() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(20);
        queue.add(15);

        assertTrue(queue.remove(20));
        assertEquals(2, queue.size());
        assertFalse(queue.remove(20));
    }

    @Test
    public void testRemoveHeadElement() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(20);
        queue.add(5);

        assertTrue(queue.remove(5));
        assertEquals(2, queue.size());
        assertEquals(10, queue.peek());
    }

    @Test
    public void testPollOnEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> pq.poll());
    }

    @Test
    public void testPeekOnEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> pq.peek());
    }

    @Test
    public void testAddNullElement() {
        assertThrows(NullPointerException.class, () -> pq.add(null));
    }

    @Test
    public void testAddWithComparatorAndPoll() {
        PriorityQueue<Integer> reversePq = new PriorityQueue<>(Comparator.reverseOrder());

        reversePq.add(10);
        reversePq.add(20);
        reversePq.add(5);

        assertEquals(20, reversePq.poll());
        assertEquals(10, reversePq.poll());
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 20; i++) {
            pq.add(i);
        }

        assertEquals(20, pq.size());
        assertEquals(0, pq.peek());
    }
}