package books;

import java.util.Arrays;

public class CommonElementsInSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {13, 27, 35, 40, 49, 55, 59};
        int[] arr2 = {17, 35, 39, 40, 55, 58, 60};
        findAllCommonElements(arr1, arr2);
        System.out.println("Merged two sorted arrays: " + Arrays.toString(mergeTwoSortedArray(arr1, arr2)));

    }

    //Two sorted array and same size with common elements
    private static void findAllCommonElements(int[] arr1, int[] arr2) {
        for (int i = 0, j = 0; i < arr1.length && j < arr1.length; ) {
            if (arr1[i] == arr2[j]) {
                System.out.println(arr1[i]);
            }
            if (arr1[i] > arr2[j]) {
                j++;
            } else {
                i++;
            }
        }
    }

    private static int[] mergeTwoSortedArray(int[] first, int[] second) {
        int[] mergedArray = new int[first.length + second.length];
        int i = 0, j = 0, z = 0;
        for (; i < first.length && j < second.length; z++) {
            if (first[i] == second[j]) {
                mergedArray[z++] = first[i++];
                mergedArray[z] = second[j++];
            } else if (first[i] < second[j]) {
                mergedArray[z] = first[i++];
            } else {
                mergedArray[z] = second[j++];
            }
        }
        for (; i < first.length; i++, z++) {
            mergedArray[z++] = first[i];
        }
        for (; j < second.length; j++, z++) {
            mergedArray[z] = second[j];
        }
        return mergedArray;
    }
}
