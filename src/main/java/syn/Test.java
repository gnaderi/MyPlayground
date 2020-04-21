package syn;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static long maximumProfit(List<Integer> price) {
        Integer[] prices = price.toArray(new Integer[price.size()]);
        long profit = 0L;
        int maxSoFar = 0;
        for (int i = prices.length - 1; i > -1; i--) {
            if (prices[i] >= maxSoFar) {
                maxSoFar = prices[i];
            }
            profit += maxSoFar - prices[i];
        }
        return profit;
    }

    public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
        Integer max = 0;

        Integer[] quantities = bundleQuantities.toArray(new Integer[0]);
        Arrays.sort(quantities);
        int i = 0;
        while (n > 0 && i < quantities.length) {
            Integer cost = bundleCosts.get(findIndex(bundleQuantities, quantities[i]));
            if (cost <= n) {
                max += quantities[i];
                n = n - cost;
            } else {
                i++;
            }
        }
        return max;
    }

    private static int findIndex(List<Integer> quantity, Integer value) {
        for (int i = 0; i < quantity.size(); i++) {
            if (quantity.get(i).equals(value))
                return i;
        }
        return -1;
    }

}
