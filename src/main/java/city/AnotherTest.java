package city;

import java.util.Arrays;

/**
 * Created by gnaderi on 12/06/2017.
 */
public class AnotherTest {
    public static void main(String[] args) {
        AnotherTest anotherTest = new AnotherTest();
        int[] A = {4, 35, 80, 123, -12345, 44, 8, -5, 24,3,678,-13467, 13,457};
        anotherTest.solution(A, 134);

    }

    public void solution(int[] A, int k) {
        if(k>A.length){
            k=A.length;
        }
        int cellSize = (findMaxNumberSize(A.clone()) + "").length();
        int counter = 0;
        while (counter < A.length) {
            printHeaderOfRow(k, cellSize);
            System.out.println();
            printDataOfRow(A, counter, k,cellSize);
            System.out.println();
            counter += k;

        }
        printHeaderOfRow(A.length-counter+k, cellSize);

    }

    private void printDataOfRow(int[] a, int counter, int k,int cellSize) {

        int l = k + counter;
        for (int i = counter; i < l&&i<a.length; i++) {
            System.out.print("|" + String.format("%" + cellSize + "s", a[i]));
        }
        System.out.print("|");
    }

    private void printHeaderOfRow(int k, int cellSize) {
        for (int i = 0; i < k ; i++) {
            System.out.print("+");
            for (int j = 0; j < cellSize; j++)
                System.out.print("-");

        }
        System.out.print("+");
    }

    private int findMaxNumberSize(int[] a) {
        Arrays.sort(a);
        return a[a.length - 1];

    }
}
