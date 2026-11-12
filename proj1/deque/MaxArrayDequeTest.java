package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        MaxArrayDeque<String> lld1 = new MaxArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        MaxArrayDeque<String>  lld1 = new MaxArrayDeque<String>();
        MaxArrayDeque<Double>  lld2 = new MaxArrayDeque<Double>();
        MaxArrayDeque<Boolean> lld3 = new MaxArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void randomDiffTest() {
        MaxArrayDeque<Integer> lld = new MaxArrayDeque<>();
        LinkedList<Integer> ll = new LinkedList<>();
        int N = 200000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                lld.addFirst(randVal);
                ll.addFirst(randVal);
                assertEquals(lld.size(), ll.size());
                assertEquals(lld.isEmpty(), ll.isEmpty());
                for (int j = 0; j < lld.size(); j++) {
                    assertEquals(lld.get(j), ll.get(j));
                }
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                lld.addLast(randVal);
                ll.addLast(randVal);
                assertEquals(lld.size(), ll.size());
                assertEquals(lld.isEmpty(), ll.isEmpty());
                for (int j = 0; j < lld.size(); j++) {
                    assertEquals(lld.get(j), ll.get(j));
                }
            } else if (operationNumber == 2) {
                assertEquals(lld.size(), ll.size());
                if (lld.size() > 0) {
                    lld.removeFirst();
                    ll.removeFirst();
                    assertEquals(lld.size(), ll.size());
                    assertEquals(lld.isEmpty(), ll.isEmpty());
                    for (int j = 0; j < lld.size(); j++) {
                        assertEquals(lld.get(j), ll.get(j));
                    }
                }
            } else if (operationNumber == 3) {
                assertEquals(lld.size(), ll.size());
                if (lld.size() > 0) {
                    lld.removeLast();
                    ll.removeLast();
                    assertEquals(lld.size(), ll.size());
                    assertEquals(lld.isEmpty(), ll.isEmpty());
                    for (int j = 0; j < lld.size(); j++) {
                        assertEquals(lld.get(j), ll.get(j));
                    }
                }
            } else if (operationNumber == 4) {
                if (lld.size() > 0) {
                    int index = StdRandom.uniform(0, lld.size());
                    assertEquals(lld.get(index), ll.get(index));
                }
            } else if (operationNumber == 5) {
                Iterator<Integer> lldi = lld.iterator();
                Iterator<Integer> lli = lld.iterator();
                assertEquals(lldi.hasNext(), lli.hasNext());
                while (lldi.hasNext()) {
                    assertEquals(lldi.hasNext(), lli.hasNext());
                    assertEquals(lldi.next(), lli.next());
                }
            } else {
                assertEquals(lld.equals(ll), ll.equals(lld));
            }
        }
    }

    @Test
    public void maxTest() {
        LinkedList<Integer> reference = new LinkedList<>();
        MaxArrayDeque<Integer> tested = new MaxArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int random = StdRandom.uniform(0, 1000);
                reference.addFirst(random);
                tested.addFirst(random);
            }
            assertEquals(
                    tested.max(new Comparator<Integer>() {
                        @Override
                        public int compare(Integer t0, Integer t1) {
                            return t1 - t0;
                        }
                    }),
                    reference.stream().max(Comparator.reverseOrder()).get()
            );
        }
    }
}
