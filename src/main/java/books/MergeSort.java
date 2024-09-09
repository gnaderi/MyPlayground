package books;

import java.util.Arrays;

public class MergeSort {
    public static void merge(int[] array, int[] leftHalf, int[] rightHalf, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (leftHalf[i] <= rightHalf[j]) {
                array[k++] = leftHalf[i++];
            } else {
                array[k++] = rightHalf[j++];
            }
        }
        while (i < left) {
            array[k++] = leftHalf[i++];
        }
        while (j < right) {
            array[k++] = rightHalf[j++];
        }
    }

    public static void mergeSort(int[] array, int length) {
        if (length <= 1) {
            //Already sorted!
            return;
        }
        int mid = length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[length - mid];
        System.arraycopy(array, 0, leftHalf, 0, mid);
        System.arraycopy(array, mid, rightHalf, 0, length - mid);

        mergeSort(leftHalf, mid);
        mergeSort(rightHalf, length - mid);

        merge(array, leftHalf, rightHalf, mid, length - mid);
    }


    public static void main(String[] args) {

        int[] testScores = {6, 9, 8, 2, 4, 1,-6};
        int size = testScores.length;

        System.out.println("Original Array " + Arrays.toString(testScores) + "\n");

        mergeSort(testScores, size);

        System.out.println("After Merge Sort " + Arrays.toString(testScores) + "\n");
    }
}
