package pool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParallelSearchTest {

    @Test
    public void computeRecursiveTest() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
        ArrayList<Integer> arrayList = new ArrayList<>();
        Arrays.stream(numbers).forEach(
                arrayList::add
        );
        ParallelSearch<Integer> parallelSearch = new ParallelSearch<>(arrayList, 35);
        assertThat(parallelSearch.compute(), is(34));
    }

    @Test
    public void computeLineTest() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        ArrayList<Integer> arrayList = new ArrayList<>();
        Arrays.stream(numbers).forEach(
                arrayList::add
        );
        ParallelSearch<Integer> parallelSearch = new ParallelSearch<>(arrayList, 1);
        assertThat(parallelSearch.compute(), is(0));
    }

    @Test
    public void noSuchItemTest() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        ArrayList<Integer> arrayList = new ArrayList<>();
        Arrays.stream(numbers).forEach(
                arrayList::add
        );
        ParallelSearch<Integer> parallelSearch = new ParallelSearch<>(arrayList, 500);
        assertThat(parallelSearch.compute(), is(-1));
    }
}
