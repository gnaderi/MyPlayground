public class Search2DMatrix {
    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {1,     3,      5,      7,      8},
                {10,    11,     16,     20,    21},
                {23,    30,     34,     50,    52},
                {60,    70,     80,     500,  550},
                {600,   700,    800,    5000, 5001}
        };
        System.out.println("Result = " + new Search2DMatrix().searchMatrix(matrix, 60));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        return search(target, matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    private static boolean search(int target, int[][] matrix, int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        int pri = startRowIndex + (endRowIndex - startRowIndex) / 2;
        int pci = startColIndex + (endColIndex - startColIndex) / 2;
        if (pri < 0 || pci < 0 || pri >= matrix.length || pci >= matrix[0].length || startRowIndex > endRowIndex || startColIndex > endColIndex) {
            return false;
        }
        int pivot = matrix[pri][pci];
        if (pivot == target) {
            return true;
        }
        if (pivot < target) {
            return search(target, matrix, pri + 1, startColIndex, endRowIndex, pci)
                    || search(target, matrix, pri, pci + 1, endRowIndex, endColIndex);
        } else {
            return search(target, matrix, startRowIndex, startColIndex, pri, pci - 1)
                    || search(target, matrix, startRowIndex, pci, pri - 1, endColIndex);
        }
    }
}