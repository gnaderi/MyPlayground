package books;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.swap;

public class SelectionSort {
    public static void main(String[] args) {
        int[] data = {-1, 1, 2, 3, 0, -1, 12, -3};
        System.out.println("Raw Data= " + Arrays.toString(data.clone()));
        System.out.println("Data after sort= " + Arrays.toString(selectionSort(data.clone())));

    }

    public static int[] selectionSort(int[] values) {
        for (int i = 0; i < values.length ; i++) {
            int minIndex = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[minIndex]) {
                    minIndex = j;
                }
                swap(values, minIndex, i);
            }
        }
        return values;
    }
}
