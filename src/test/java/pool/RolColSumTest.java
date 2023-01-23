package pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RolColSumTest {
    @Test
    public void sumTest() {
        int[][] matrix = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36}};
        Sums[] sums = RolColSum.sum(matrix);
        assertThat(sums[3].getColSum(), is(114));
        assertThat(sums[3].getRowSum(), is(129));
        assertThat(sums[0].getColSum(), is(96));
        assertThat(sums[0].getRowSum(), is(21));
        assertThat(sums[5].getColSum(), is(126));
        assertThat(sums[5].getRowSum(), is(201));
    }

    @Test
    public void asyncSumTest() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36}};
        Sums[] sums = RolColSum.asyncSum(matrix);
        assertThat(sums[3].getColSum(), is(114));
        assertThat(sums[3].getRowSum(), is(129));
        assertThat(sums[0].getColSum(), is(96));
        assertThat(sums[0].getRowSum(), is(21));
        assertThat(sums[5].getColSum(), is(126));
        assertThat(sums[5].getRowSum(), is(201));
    }
}
