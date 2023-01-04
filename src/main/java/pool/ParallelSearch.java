package pool;

import java.util.concurrent.ForkJoinPool;
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

        ParallelSearch<T> leftSearch = new ParallelSearch<>(array, value, from, mid);
        ParallelSearch<T> rightSearch = new ParallelSearch<>(array, value, mid, to);

        leftSearch.fork();
        rightSearch.fork();

        int left = leftSearch.join();
        int right = rightSearch.join();

        return Math.max(left, right);
    }


    private int indexSearch(Object[] array, Object value, int from, int to) {

        for (int i = from; i < to; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int indexOf(T[] array, T value) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearch<>(array, value, 0, array.length - 1));
    }
}

