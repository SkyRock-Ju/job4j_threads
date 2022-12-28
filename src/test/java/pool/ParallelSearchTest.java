package pool;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParallelSearchTest {

    @Test
    public void computeRecursiveTest() {
        Object[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
        ParallelSearch<Object> parallelSearch = new ParallelSearch<>(numbers, 35,0,numbers.length);
        assertThat(parallelSearch.compute(), is(34));
    }

    @Test
    public void computeLineTest() {
        Object[] numbers = {1, 2, 3, 4, 5, 6};
        ParallelSearch<Object> parallelSearch = new ParallelSearch<>(numbers, 1,0,numbers.length);
        assertThat(parallelSearch.compute(), is(0));
    }

    @Test
    public void noSuchItemTest() {
        Object[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        ParallelSearch<Object> parallelSearch = new ParallelSearch<>(numbers, 500,0,numbers.length);
        assertThat(parallelSearch.compute(), is(-1));
    }

    @Test
    public void differentTypesTest() {
        Object[] numbers = {"5", '2', 3.3, 4, 5f, 6d, 7, 8, "test", 10};
        ParallelSearch<Object> parallelSearch = new ParallelSearch<>(numbers, "test",0,numbers.length);
        assertThat(parallelSearch.compute(), is(8));
    }
}
