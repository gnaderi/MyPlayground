package books;

class PositiveNum {
    public int firstMissingPositive(int[] nums) {
        int ptr = 0;
        int n=nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                ptr = 1;
                break;
            }
        }

        if (ptr == 0)
            return (1);

        for (int i = 0; i < n; i++)
            if (nums[i] <= 0 || nums[i] > n)
                nums[i] = 1;
        for (int i = 0; i < n; i++)
            nums[(nums[i] - 1) % n] += n;

        for (int i = 0; i < n; i++)
            if (nums[i] <= n)
                return (i + 1);
        return (n + 1);
    }
}