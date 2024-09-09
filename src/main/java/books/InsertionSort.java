package books;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.swap;


public class InsertionSort {
    public static void main(String[] args) {
        int[] data = {-1, 1, 2, 3, 0, -1, -1, 1, 0, 12, -3};
        System.out.println("Raw Data= " + Arrays.toString(data.clone()));
        System.out.println("Data after sort= " + Arrays.toString(insertionSort(data)));

    }

    public static int[] insertionSort(int[] values) {
        for (int i = 0; i < values.length; i++) {
            int j = i;
            while (j > 0 && values[j] < values[j - 1]) {
                swap(values, j - 1, j);
                j--;
            }
        }
        return values;
    }
}
