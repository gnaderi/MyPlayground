package books;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.ArrayUtils.swap;

public class RandomizeQuickSortTwoPointer {
    static final Random random = new Random();

    public static void main(String[] args) {
        testQuickSort();
    }

    private static void testQuickSort() {
        int[][] testArray = new int[100][100];
        for (int i = 0; i < 100; i++) {
            testArray[i] = IntStream.range(0, 1000).map(el -> ThreadLocalRandom.current().nextInt(-1000, 1000)).toArray();
        }
        for (int i = 0; i < 100; i++) {
            int[] clone = testArray[i].clone();
            Arrays.sort(clone);
            int[] clone1 = ranQuickSort(testArray[i].clone(), 0, testArray[i].length - 1);
            if (!Arrays.equals(clone, clone1)) {
                System.out.println(" ===============================");
                System.err.println("SysqSort: " + Arrays.toString(clone));
                System.err.println("MyqSort: " + Arrays.toString(clone1));

            }
        }
    }


    private static int[] ranQuickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = ranPartition(nums, start, end);
            ranQuickSort(nums, start, pivot - 1);
            ranQuickSort(nums, pivot + 1, end);
        }
        return nums;
    }

    private static int ranPartition(int[] nums, int start, int end) {
        // Choosing a random pivot
        swap(nums, start + random.nextInt(end - start + 1), end);
        int pivot = nums[end];
        int leftPointer = start;
        int rightPointer = end;
        while (leftPointer < rightPointer) {
            while (nums[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (nums[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(nums, leftPointer, rightPointer);
        }
        swap(nums, leftPointer, end);
        return rightPointer;
    }
}
