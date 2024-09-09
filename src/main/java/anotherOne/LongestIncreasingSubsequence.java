package anotherOne;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int length = new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        System.out.println("length = " + length);
    }

    public int lengthOfLIS(int[] nums) {
        List<Integer> al = new ArrayList<>();
        al.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > al.get(al.size() - 1)) {
                al.add(nums[i]);
            } else {
                int j = binSearch(al, nums[i]);
                al.set(j, nums[i]);
                System.out.println("al.size() = " + al.size());
            }
        }
        return al.size();
    }

    private int binSearch(List<Integer> al, int val) {
        int low = 0;
        int high = al.size() - 1;
        while (high > low) {
            int mid = low + (high - low) / 2;
            if (al.get(mid) < val) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }
}