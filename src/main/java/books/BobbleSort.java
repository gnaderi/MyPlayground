package books;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.swap;

public class BobbleSort {
    public static void main(String[] args) {
        int[] data = {-1, 1, 2, 3, 0, -1, -1, 1, 0, 12, -3};
        System.out.println("Raw Data= " + Arrays.toString(data.clone()));
        System.out.println("Data after sort= " + Arrays.toString(bobbleSort(data.clone())));

    }

    public static int[] bobbleSort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < values.length - i - 1; j++) {
                if (values[j] > values[j + 1]) {
                    swap(values, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                //no swap done so all already sorted don't waste more time!
                return values;
            }
        }
        return values;
    }
}
