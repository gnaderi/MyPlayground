package books;

public class FindMinInSortedRotatedArray {
    public static void main(String[] args) {
        System.out.println("new FindMinInSortedRotatedArray().findMin(new int[]{4, 5, 6, 1, 2, 3}) = "
                + new FindMinInSortedRotatedArray().findMin(new int[]{7,8,9, 1, 2, 3, 4, 5, 6}));
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int low = 0;
        int high = nums.length - 1;
        if (nums[low] < nums[high]) {
            return nums[low];
        }
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
                ans = Math.min(ans, nums[mid]);
                low++;
                high--;
            }
            // If the left half is sorted, the minimum
            // element must be in the right half
            else if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            // If the right half is sorted, the minimum
            // element must be in the left half
            else {
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }
}
