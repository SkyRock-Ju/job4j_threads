package pool;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final List<T> array;
    private final T value;

    ParallelSearch(List<T> array, T value) {
        this.array = array;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        int result = -1;
        if (array.size() <= 10) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).equals(value)) {
                    return i;
                }
            }
        } else {
            int mid = array.size() / 2;
            ParallelSearch<T> leftSearch = new ParallelSearch<>(array.subList(0, mid), value);
            ParallelSearch<T> rightSearch = new ParallelSearch<>(array.subList(mid, array.size()), value);
            leftSearch.fork();
            rightSearch.fork();
            int left = leftSearch.join();
            int right = rightSearch.join();

            if (left > right) {
                result = left;
            } else if (right > left) {
                result = right + mid;
            }
        }
        return result;
    }
}
