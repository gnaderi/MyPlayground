package books;

import java.util.HashSet;
import java.util.Set;

class PermutationOfSmallerStringInBiggerString {
    public static void main(String[] args) {
        System.out.println("Pairs = " + new PermutationOfSmallerStringInBiggerString().kDiffPairsCounter(new int[]{1, 7, 5, 9, 2, 12, 3}, 2));
        new PermutationOfSmallerStringInBiggerString().findAllPermutationOfSmallerStringInBiggerString("abbc", "cbabadcbbabbcbabaabccbabc");
        new PermutationOfSmallerStringInBiggerString().findAllPermutationOfSmallerStringInBiggerString("abcd", "bacdgabcda");
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

    void findAllPermutationOfSmallerStringInBiggerString(String small, String biggerStr) {
        int[] sCounter = new int[128];
        int[] bCounter = new int[128];
        int sl = small.length();
        for (int i = 0; i < sl; i++) {
            sCounter[small.charAt(i) - 'a']++;
            bCounter[biggerStr.charAt(i) - 'a']++;
        }
        for (int i = sl; i < biggerStr.length(); i++) {
            if (compareCounters(sCounter, bCounter)) {
                System.out.println("Found at location:" + (i - sl));
            }
            bCounter[biggerStr.charAt(i) - 'a']++;

            bCounter[biggerStr.charAt(i - sl) - 'a']--;
        }
        if (compareCounters(sCounter, bCounter)) {
            System.out.println("Found at location:" + (biggerStr.length() - sl));
        }
    }

    boolean compareCounters(int[] sCounter, int[] bCounter) {
        for (int i = 0; i < sCounter.length; i++)
            if (sCounter[i] != bCounter[i])
                return false;
        return true;
    }
}