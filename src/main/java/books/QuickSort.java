package books;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] data = {-1, 1, 2, 3, 0, -1, 12, -3};
        System.out.println("Quick Raw Data= " + Arrays.toString(data.clone()));
        new QuickSort().quickSort(data, 0, data.length - 1);
        System.out.println("Quick Sorted Data= " + Arrays.toString(data));
    }

    public void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pi = pivot(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }

    private int pivot(int[] data, int low, int high) {
        int pivot = data[high];
        int left = low;
        for (int i = low; i < high; i++) {
            if (data[i] < pivot) {
                swap(data, left, i);
                System.out.printf("Pivot:%s Data:%s%n", pivot, Arrays.toString(data));
                left += 1;
            }
        }
        swap(data, left, high);
        System.out.printf("Pivot:%s Data:%s%n", pivot, Arrays.toString(data));
        return left;
    }


    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
