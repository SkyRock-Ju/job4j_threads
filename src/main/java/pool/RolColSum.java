package pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        int[] rowSums = new int[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                rowSums[row] += matrix[row][col];
            }
        }
        int[] colSums = new int[matrix[0].length];
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                colSums[col] += matrix[row][col];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            sums[i].setColSum(colSums[i]);
            sums[i].setRowSum(rowSums[i]);
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
        return CompletableFuture.supplyAsync(() -> {
            int rowSum = 0;
            int colSum = 0;
            for (int col = 0; col < matrix.length; col++) {
                colSum += matrix[col][index];
            }
            for (int row = 0; row < matrix.length; row++) {
                rowSum += matrix[index][row];
            }
            return new int[]{rowSum, colSum};
        });
    }
}