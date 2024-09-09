package books;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class MostFrequentNumber {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] nums = IntStream.range(1, 100).map(rand::nextInt).toArray();
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println("mostFrequent(nums) = " + mostFrequent(nums));
        System.out.println("notTween = " + notTweenNumber(new int[]{8, 4, 5, 2, 2, 3, 3, 4, 5, 6, 7, 8, 7}));
    }

    public static int mostFrequent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int maxFreq = -1;
        int maxFreqValue = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxFreqValue) {
                maxFreqValue = entry.getKey();
                maxFreq = entry.getValue();
            }
        }
        return maxFreq;
    }

    public static int notTweenNumber(int[] nums) {
        double sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        sum = sum / 2.0;
        double sum2 = 0;
        for (int num : nums) {
            sum2 = sum2 + num / 2.0;
        }
        return (int)((sum - sum2) * 2);
    }
}
