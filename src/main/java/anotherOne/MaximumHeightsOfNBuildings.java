package anotherOne;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MaximumHeightsOfNBuildings {
    public static void main(String[] args) {
//        int[] a = {-3, 2, 4, -5, 3};
//        System.out.println("a = " + new MaximumHeightsOfNBuildings().solution(a));
        int[] a2 = {9, 4, 3, 7, 7};
        int[] a21 = {2, 5, 4, 5, 5};

        System.out.println("a2 = " + Arrays.toString(new MaximumHeightsOfNBuildings().solution2(new int[]{1,2,2})));
    }

    /*
    Question: following answer is not correct iven the maximum heights of N buildings that need to be erected, an array A of N numbers is provided. The task is to determine the actual heights of the skyscrapers, ensuring the following conditions are met:The height of the K-th skyscraper should be positive and not greater than A[K].The height of any two buildings
    following answer is not correct iven the maximum heights of N buildings that need to be erected, an array A of N numbers is provided. The task is to determine the actual heights of the skyscrapers, ensuring the following conditions are met:
    The height of the K
    -
    1)th skyscraper should be positive and not greater than A[K].
    2)The height of any two buildings shouldn't be the same.
    3)The highest potential total height for all of the towers should be achieved.
    Explanation:

    The solution function takes an array A as input and computes the heights of the skyscrapers as described in the problem statement.
    The function first determines the length of the array A and then creates a sorted copy of A in descending order, which is stored in the variable sortedA.
    An empty array result is initialized to store the heights of the skyscrapers.
    The function iterates through the sorted array sortedA and assigns the heights to the skyscrapers. It ensures that no two skyscrapers have the same height by decreasing the height by
    ﻿if two consecutive elements are found to be the same.
    The result array stores the heights of the skyscrapers at the corresponding indices.
    The function finally returns the array result with the computed heights.
    Explanation:
    The solution runs with a time complexity of
    ﻿due to the sorting operation, where N is the size of the input array.
     */
    public int[] solution2(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            if (set.contains(A[i])) {
                while (set.contains(A[i])) {
                    A[i] = A[i] - 1;
                }
                set.add(A[i]);

            } else {
                set.add(A[i]);
            }
        }
        return A;
    }

    public int solution(int[] A) {
        int minNumberOfTrees = 0;
        int diff;
        if (A.length == 1) {
            minNumberOfTrees = -1 * Math.min(0, A[0]);
        }
        if (A.length > 1 && (A[0] + A[1]) < 0) {
            diff = Math.abs(A[0] + A[1]);
            A[1] += diff;
            minNumberOfTrees += diff;
        }
        ;
        if (A.length > 2 && (A[A.length - 1] + A[A.length - 2]) < 0) {
            diff = Math.abs(A[A.length - 1] + A[A.length - 2]);
            A[A.length - 2] += diff;
            minNumberOfTrees += diff;
        }

        for (int i = 1; i < A.length - 1; ++i) {
            if (A[i - 1] + A[i] + A[i + 1] >= 0) continue;
            diff = Math.abs(A[i - 1] + A[i] + A[i + 1]);
            A[i + 1] += diff;
            minNumberOfTrees += diff;
        }

        return minNumberOfTrees;
    }
}
