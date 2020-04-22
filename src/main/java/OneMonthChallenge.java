import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class OneMonthChallenge {
    /**
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * <p>
     * Note:
     * <p>
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * <p>
     * Example 1:
     * <p>
     * Input: [2,2,1]
     * Output: 1
     * Example 2:
     * <p>
     * Input: [4,1,2,1,2]
     * Output: 4
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        Optional<Integer> singleNumber = map.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst();
        return singleNumber.get();

    }

    /**
     * Happy Number
     * Solution
     * Write an algorithm to determine if a number n is "happy".
     * <p>
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     * <p>
     * Return True if n is a happy number, and False if not.
     * <p>
     * Example:
     * <p>
     * Input: 19
     * Output: true
     * Explanation:
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int tempN = n;

        while (true) {
            int firstSum = sumSqrtDigits(n);
            int secondSum = sumSqrtDigits(sumSqrtDigits(tempN));
            if (secondSum == 1 || firstSum == 1) {
                return true;
            }
            if (secondSum == firstSum) {
                return false;
            }
            tempN = secondSum;
            n = firstSum;
        }
    }

    private int sumSqrtDigits(int num) {
        int sum = 0;

        while (num > 0) {
            sum += (int) Math.pow(num % 10, 2);
            num = num / 10;
        }
        return sum;
    }

    /**
     * Maximum Subarray
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * Example:
     * <p>
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Follow up:
     * <p>
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     *
     * @param nums
     * @return
     */

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        long runSum = Integer.MIN_VALUE;
        long result = Integer.MIN_VALUE;
        for (int num : nums) {
            runSum = Math.max(num, runSum + num);
            result = Math.max(result, runSum);
        }

        return (int) result;
    }

    public int maxSubArray2(int[] nums) {
        int size = nums.length;

        int besRun = Integer.MIN_VALUE;
        int currentRun = 0;

        for (int i = 0; i < size; i++) {
            if (nums[i] > currentRun + nums[i]) {
                currentRun = nums[i];
            } else
                currentRun = currentRun + nums[i];

            if (besRun < currentRun) {
                besRun = currentRun;
            }
        }
        return besRun;
    }

    public int maxSubArray(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        return maxSumSubarray(nums, low, high);
    }

    private int maxSumSubarray(int[] nums, int low, int high) {
        if (high == low) {
            return nums[high];
        }
        int mid = (high + low) / 2;

        int maxSumSubarrayLow = maxSumSubarray(nums, low, mid);
        int maxSumSubarrayHigh = maxSumSubarray(nums, mid + 1, high);
        int crossingSubarray = maxCrossingSubarray(nums, low, mid, high);
        return Math.max(Math.max(maxSumSubarrayLow, maxSumSubarrayHigh), crossingSubarray);
    }

    private int maxCrossingSubarray(int nums[], int low, int mid, int high) {

        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + nums[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for (int i = mid + 1; i <= high; i++) {
            sum = sum + nums[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        return (leftSum + rightSum);
    }

    /**
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * <p>
     * Example:
     * <p>
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Note:
     * <p>
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     *
     * @param nums
     */
    public int[] moveZeroes(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left++;
            }
        }
        return nums;
    }

    /**
     * Best Time to Buy and Sell Stock II
     * Say you have an array prices for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     * <p>
     * Example 1:
     * <p>
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     * Example 2:
     * <p>
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     * engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     * <p>
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */

    public int maxProfit(int[] prices) {

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

    /**
     * Group Anagrams
     * Given an array of strings, group anagrams together.
     * <p>
     * Example:
     * <p>
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note:
     * <p>
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            List<String> list = map.computeIfAbsent(str.hashCode(), k -> new ArrayList<>());
            list.add(str);
            map.put(str.hashCode(), list);
        }
        List<List<String>> groupByList = map.values().stream().collect(Collectors.toList());
        return groupByList;
    }
}
