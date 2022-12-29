package pool;

import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T value;
    private final int from;
    private final int to;

    public ParallelSearch(T[] array, T value, int from, int to) {
        this.array = array;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return indexSearch(array, value, from, to);
        }
        int mid = (from + to) / 2;

        ParallelSearch<Object> leftSearch = new ParallelSearch<>(array, value, from, mid);
        ParallelSearch<Object> rightSearch = new ParallelSearch<>(array, value, mid, to);

        leftSearch.fork();
        rightSearch.fork();

        int left = leftSearch.join();
        int right = rightSearch.join();

        if (left != right) {
            return Math.max(left, right);
        }
        return -1;
    }


    private static int indexSearch(Object[] array, Object value, int from, int to) {

        for (int i = from; i < to; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}

