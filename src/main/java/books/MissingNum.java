package books;

/*
Given an array containing n distinct numbers taken
from 0, 1, 2, ..., n, find the one that is missing from the array.
For example,
Given nums = [0, 1, 3] return 2.
Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?

 */
public class MissingNum {
    public static void main(String[] args) {
        System.out.println("MissingNum = " + getMissingNo(new int[]{2, 3, 4, 5, 7, 1, 0, 8, 6}));
        System.out.println("MissingNum = " + getMissingNoFrom1toNPlusOneLogN(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println("MissingNum = " + getMissingNo2(new int[]{2, 3, 4, 5, 7, 1, 0, 8, 6}));
    }

    static int getMissingNo(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }



    static long getMissingNo2(int[] nums) {
        int sum = nums.length * (nums.length + 1) / 2;
        for (int num : nums) sum -= num;
        return sum;
    }

    static long getMissingNoFrom1toNPlusOneLogN(int[] nums) {
        if (nums[0] != 1)
            return 1;
        if (nums[nums.length - 1] != (nums.length + 1))
            return nums.length + 1;

        int left = 0;
        int right = nums.length - 1;
        while ((right - left) > 1) {
            int mid = (left + right) / 2;
            if ((nums[left] - left) != (nums[mid] - mid)) {
                right = mid;
            } else if ((nums[right] - right) != (nums[mid] - mid)) {
                left = mid;
            }
        }
        return (nums[left] + 1);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergeNums = new int[nums1.length + nums2.length];
        int idxNums1 = 0;
        int idxNums2 = 0;
        for (int i = 0; i < mergeNums.length; i++) {
            if (idxNums2 < nums2.length && (idxNums1 == nums1.length || nums2[idxNums2] < nums1[idxNums1])) {
                mergeNums[i] = nums2[idxNums2++];
            } else {
                mergeNums[i] = nums1[idxNums1++];
            }
        }
        int mergeNum = mergeNums[mergeNums.length / 2];
        if (mergeNums.length % 2 == 1) {
            return mergeNum;
        } else {
            return (mergeNum + mergeNums[mergeNums.length / 2 - 1]) / 2.0;
        }

    }
}