package pool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {
    private final Object[] array;
    private final Object value;

    ParallelSearch(Object[] array, Object value) {
        this.array = array;
        this.value = value;
    }

    @Override
    protected Integer compute() {

        if (array.length <= 10) {
            return indexSearch(array, value);
        }
        int mid = array.length / 2;
        ParallelSearch leftSearch = new ParallelSearch(Arrays.copyOfRange(array, 0, mid), value);
        ParallelSearch rightSearch = new ParallelSearch(Arrays.copyOfRange(array, mid, array.length), value);

        leftSearch.fork();
        rightSearch.fork();

        int left = leftSearch.join();
        int right = rightSearch.join();

        if (left > right) {
            return left;
        } else if (right > left) {
            return right + mid;
        }
        return -1;
    }

    private static int indexSearch(Object[] array, Object value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}

