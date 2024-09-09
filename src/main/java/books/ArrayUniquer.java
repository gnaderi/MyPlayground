package books;

class ArrayUniquer {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2};
        ArrayUniquer arrayUniquer = new ArrayUniquer();
        System.out.println("arrayUniquer1 = " + arrayUniquer.minIncrementForUnique(nums1));
        int[] nums2 = {3, 2, 1, 2, 1, 7};
        System.out.println("arrayUniquer2 = " + arrayUniquer.minIncrementForUnique(nums2));

    }


    public int minIncrementForUnique(int[] nums) {
        int n = nums.length;
        int max = 0;
        int minIncrements = 0;

        // Find maximum value in array to determine range of frequencyCount array
        for (int val : nums) {
            max = Math.max(max, val);
        }

        // Create a frequencyCount array to store the frequency of each value in nums
        int[] frequencyCount = new int[n + max];

        // Populate frequencyCount array with the frequency of each value in nums
        for (int val : nums) {
            frequencyCount[val]++;
        }

        // Iterate over the frequencyCount array to make all values unique
        for (int i = 0; i < frequencyCount.length; i++) {
            if (frequencyCount[i] <= 1) continue;

            // Determine excess occurrences, carry them over to the next value,
            // ensure single occurrence for current value, and update minIncrements.
            int duplicates = frequencyCount[i] - 1;
            frequencyCount[i + 1] += duplicates;
            frequencyCount[i] = 1;
            minIncrements += duplicates;
        }

        return minIncrements;
    }
}