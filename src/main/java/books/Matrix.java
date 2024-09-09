package books;

public class Matrix {
    public static void rotate90(int[][] matrix) {
        int n = matrix.length;

        // transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        System.out.println("Transforming rows into columns Matrix : ");
        displayMatrix(matrix);

        // reverse each column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(matrix, j, i, n - j - 1, i);
            }
        }
    }

    private static void swap(int[][] matrix, int i, int j, int k, int l) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[k][l];
        matrix[k][l] = temp;
    }

    static void displayMatrix(int[][] sqMatrix) {
        for (int i = 0; i < sqMatrix.length; i++) {
            for (int j = 0; j < sqMatrix[0].length; j++)
                System.out.print(" " + sqMatrix[i][j]);

            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void zeroMatrix(int[][] matrix) {
        int n = matrix.length;
        boolean[] zeroRows = new boolean[n];
        boolean[] zeroCols = new boolean[n];
        // transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }
        for (int i = 0; i < zeroRows.length; i++) {
            for (int j = 0; j < zeroCols.length; j++) {
                if (zeroRows[i] || zeroCols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] sqMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 0, 11, 12}, {13, 14, 15, 16}};
        System.out.println("Before rotation Matrix : ");
        displayMatrix(sqMatrix);

        rotate90(sqMatrix);
        System.out.println("After rotation Matrix 90° : ");
        displayMatrix(sqMatrix);


        zeroMatrix(sqMatrix);
        System.out.println("After zeroMatrix : ");
        displayMatrix(sqMatrix);
    }

}