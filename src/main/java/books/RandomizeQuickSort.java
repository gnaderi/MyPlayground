package books;

import java.util.Arrays;
import java.util.Random;

public class RandomizeQuickSort {
    static final Random random = new Random();

    public static void main(String[] args) {
        int[] nums = {8, 4, 28, 4, 5, 0, -3, 14, 1, 67, -8};
        nums = ranQuickSort(nums, 0, nums.length - 1);
        System.out.println("nums = " + Arrays.toString(nums));
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
        for (int j = start; j <= end - 1; j++) {
            // If current element is smaller than the pivot
            if (nums[j] < pivot) {
                swap(nums, start, j);
                start++;
            }
        }
        swap(nums, start, end);
        return start;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
