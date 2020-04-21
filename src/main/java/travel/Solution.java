package travel;

import java.util.Stack;

import static java.lang.Math.min;

class Solution {
    public static void main(String[] args) {
//        vector< vector<int> > A = {
//                { 6, 5, 4, 4 },
//                { 4, 4, 3, 4 },
//                { 1, 3, 2, 4 },
//                { 5, 4, 2, 4 },
//                { 9, 4, 3, 4 },
//                { 7, 4, 4, 4 },
//                { 1, 4, 4, 4 }
//        };
        int[][] A = new int[6][5];
        A[0][0] = 1;
        A[1][0] = 1;
        A[2][0] = 0;
        A[3][0] = 0;
        A[4][0] = 1;
        A[5][0] = 0;
        A[0][1] = 0;
        A[1][1] = 1;
        A[2][1] = 0;
        A[3][1] = 0;
        A[4][1] = 0;
        A[5][1] = 0;
        A[0][2] = 0;
        A[1][2] = 0;
        A[2][2] = 0;
        A[3][2] = 0;
        A[4][2] = 1;
        A[5][2] = 0;
        A[0][3] = 0;
        A[1][3] = 0;
        A[2][3] = 1;
        A[3][3] = 1;
        A[4][3] = 1;
        A[5][3] = 0;
        A[0][4] = 0;
        A[1][4] = 0;
        A[2][4] = 0;
        A[3][4] = 1;
        A[4][4] = 0;
        A[5][4] = 0;

        for (int i = 0; i < A.length; i++) {
            System.out.print("|");
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j]+"|");
            }
            System.out.println();
        }
        Solution solution = new Solution();
        System.out.println("solution.solution(A) = " + solution.solution(A));
    }

    public int solution(int[][] A) {
        int rowLen = A.length;
        int colLen = A[0].length;

        if (rowLen < 2 && colLen < 2)
            return min(rowLen, colLen);

        int[][] B = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                B[i][j] = -1;
            }
        }
        int countCountries = 0;
        Pointer currentPointer = new Pointer();

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (B[row][col] == -1) {
                    currentPointer.row = row;
                    currentPointer.col = col;
                    if (!isCountryExist(A, B, currentPointer)) {
                        countCountries++;
                        B[currentPointer.row][currentPointer.col] = countCountries;
                    }

                    expandCountry(A, B, currentPointer);
                }
            }
        }

        return countCountries;
    }

    private boolean isCountryExist(int[][] A, int[][] B, Pointer position) {
        int rowLen = B.length;
        int colLen = B[0].length;
        int x = position.row;
        int y = position.col;

        if (y < colLen - 1 && B[x][y + 1] != -1 && A[x][y] == A[x][y + 1]) {
            B[x][y] = B[x][y + 1];
            return true;
        }
        if (x < rowLen - 1 && B[x + 1][y] != -1 && A[x][y] == A[x + 1][y]) {
            B[x][y] = B[x + 1][y];
            return true;
        }
        if (y > 0 && B[x][y - 1] != -1 && A[x][y] == A[x][y - 1]) {
            B[x][y] = B[x][y - 1];
            return true;
        }
        if (x > 0 && B[x - 1][y] != -1 && A[x][y] == A[x - 1][y]) {
            B[x][y] = B[x - 1][y];
            return true;
        }

        return false;
    }

    private void expandCountry(int[][] A, int[][] B, Pointer pointer) {
        Stack<Pointer> stackPointer = new Stack<>();
        stackPointer.push(pointer);

        while (!stackPointer.empty()) {
            Pointer currentPointer = stackPointer.peek();
            stackPointer.pop();

            while (hasNextBlock(A, B, currentPointer)) {
                Pointer nextPointer = moveNext(A, B, currentPointer);
                stackPointer.push(currentPointer);
                currentPointer = nextPointer;

            }
        }
    }

    private Pointer moveNext(int[][] A, int[][] B, Pointer currentPointer) {
        int num_row = B.length;
        int num_col = B[0].length;
        int x = currentPointer.row;
        int y = currentPointer.col;

        Pointer nextPointer = new Pointer();

        if (y < num_col - 1 && B[x][y + 1] == -1 && A[x][y] == A[x][y + 1]) {
            B[x][y + 1] = B[x][y];
            nextPointer.row = x;
            nextPointer.col = y + 1;
            return nextPointer;
        }
        if (x < num_row - 1 && B[x + 1][y] == -1 && A[x][y] == A[x + 1][y]) {
            B[x + 1][y] = B[x][y];
            nextPointer.row = x + 1;
            nextPointer.col = y;
            return nextPointer;
        }
        if (y > 0 && B[x][y - 1] == -1 && A[x][y] == A[x][y - 1]) {
            B[x][y - 1] = B[x][y];
            nextPointer.row = x;
            nextPointer.col = y - 1;
            return nextPointer;
        }
        if (x > 0 && B[x - 1][y] == -1 && A[x][y] == A[x - 1][y]) {
            B[x - 1][y] = B[x][y];
            nextPointer.row = x - 1;
            nextPointer.col = y;
            return nextPointer;
        }

        return nextPointer;
    }


    private boolean hasNextBlock(int[][] A, int[][] B, Pointer currentPointer) {
        int rowLen = B.length;
        int colLen = B[0].length;
        int x = currentPointer.row;
        int y = currentPointer.col;

        if (y < colLen - 1 && B[x][y + 1] == -1 && A[x][y] == A[x][y + 1]) {
            return true;
        }
        if (x < rowLen - 1 && B[x + 1][y] == -1 && A[x][y] == A[x + 1][y]) {
            return true;
        }
        if (y > 0 && B[x][y - 1] == -1 && A[x][y] == A[x][y - 1]) {
            return true;
        }
        return x > 0 && B[x - 1][y] == -1 && A[x][y] == A[x - 1][y];

    }
}

class Pointer {
    public int row = 0;
    public int col = 0;
}