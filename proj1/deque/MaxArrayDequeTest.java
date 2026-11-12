package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {

    @Test
    public void maxTest() {
        LinkedList<Integer> reference = new LinkedList<>();
        MaxArrayDeque<Integer> tested = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t0, Integer t1) {
                return t1 - t0;
            }
        });
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int random = StdRandom.uniform(0, 1000);
                reference.addFirst(random);
                tested.addFirst(random);
            }
            assertEquals(
                    tested.max(),
                    reference.stream().max(Comparator.reverseOrder()).get()
            );
        }
    }
}
