package books;

import java.util.HashSet;
import java.util.Set;

class kDifferencePairs {
    public static void main(String[] args) {
        System.out.println("Pairs = " + new kDifferencePairs().kDiffPairsCounter(new int[]{1, 7, 5, 9, 2, 12, 3}, 2));
    }


    //find pairs with k difference for a distinct integer arrays.
    int kDiffPairsCounter(int[] pairs, int k) {
        int pairCount = 0;
        Set<Integer> integerSet = new HashSet<>(pairs.length);
        for (int pair : pairs) {
            integerSet.add(pair);
        }
        for (int pair : pairs) {
            if (integerSet.contains(pair - k)) {
                pairCount++;
            }
        }
        return pairCount;
    }
}