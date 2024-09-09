package books;

public class NQueens {
    public static void main(String[] args) {
        int n = 5;
        int[] rows = new int[n];
        boolean[] used = new boolean[n];
        int total = nQueens(rows, used, 0);
        System.out.println("total = " + total);
    }

    private static int nQueens(int[] rows, boolean[] used, int i) {
        if (i == rows.length) {
            for (int j = 0; j < rows.length; j++) {
                for (int k = j + 1; k < rows.length; k++) {
                    if ((rows[j] + j == rows[k] + k) || (rows[j] - j == rows[k] - k)) {
                        return 0;
                    }
                }
            }
            return 1;
        }
        int total = 0;
        for (rows[i] = 0; rows[i] < rows.length; rows[i]++) {
            if (used[rows[i]]) {
                continue;
            }
            used[rows[i]] = true;
            total += nQueens(rows, used, i + 1);
            used[rows[i]] = false;
        }
        return total;
    }
}
