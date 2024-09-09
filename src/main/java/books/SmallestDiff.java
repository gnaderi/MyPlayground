package books;

import java.util.Arrays;

/*
Smallest Difference: Given two arrays of integers, compute the pair of values (one value in each
array) with the smallest (non-negative) difference. Return the difference.
 */
public class SmallestDiff {
    public static void main(String[] args) {
        System.out.println("Smallest Diff = " + findSmallDiff(new int[]{1, 3, 15, 11, 2}, new int[]{23, 127, 235, 19, 8}));
        System.out.println("max(5,6) = " + max(5, 6));
    }

    private static int findSmallDiff(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        final int n = arr1.length;
        final int m = arr2.length;
        int diff = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < n && j < m) {
            int curDiff = Math.abs(arr1[i] - arr2[j]);
            if (curDiff < diff) {
                diff = curDiff;
            }
            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return diff;
    }
    static  int max(int a,int b){

        return (Math.abs(a-b)+a+b)/2;
    }
}
