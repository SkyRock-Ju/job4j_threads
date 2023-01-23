package pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            int[] result = calculate(matrix, i);
            sums[i].setRowSum(result[0]);
            sums[i].setColSum(result[1]);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<int[]>> futures = new HashMap<>();
        for (int index = 0; index < matrix.length; index++) {
            sums[index] = new Sums();
            futures.put(index, sumsTask(matrix, index));
        }
        for (Integer key : futures.keySet()) {
            int[] results = futures.get(key).get();
            sums[key].setRowSum(results[0]);
            sums[key].setColSum(results[1]);
        }
        return sums;
    }

    private static CompletableFuture<int[]> sumsTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> calculate(matrix, index));
    }

    private static int[] calculate(int[][] matrix, int index) {
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            colSum += matrix[i][index];
            rowSum += matrix[index][i];
        }
        return new int[]{rowSum, colSum};
    }
}