package books;

import java.util.Arrays;

public class PancakeSort {
    public static void main(String[] args) {
        int[] pancakes = {1, 2, 3, 0, 6, 12, 4};
        sortPancakes(pancakes);

        System.out.println(Arrays.toString(pancakes));

    }

    public static void sortPancakes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int maxElement = i;
            for (int j = i; j >= 0; j--) {
                if (arr[j] > arr[maxElement]) {
                    maxElement = j;
                }
            }
            flipPancake(arr, maxElement + 1);
            flipPancake(arr, i + 1);
        }
    }

    private static void flipPancake(int[] arr, int k) {
        for (int i = 0; i < k / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[k - i - 1];
            arr[k - i - 1] = temp;
        }
    }
}
