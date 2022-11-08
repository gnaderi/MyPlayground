package hackerrank;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class PreparationKit {

    /*
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */

    public static int sockMerchant(int n, List<Integer> ar) {
        final Map<Integer, Integer> stocks = new HashMap<>();

        ar.forEach(s -> {
            stocks.merge(s, 1, Integer::sum);
        });

        return stocks.values().stream().filter(v -> v >= 2).mapToInt(i -> i / 2).sum();
    }

    public static int countingValleys(int steps, String path) {
        int valleys = 0;
        int level = 0;

        for (int i = 0; i < steps; i++) {
            if (path.charAt(i) == 'U') {
                level++;
            } else {
                level--;
            }
            if (level == 0 && path.charAt(i) == 'U') {
                valleys++;
            }
        }
        return valleys;
    }

    public static int jumpingOnClouds(List<Integer> clouds) {
        int jumps = 0;
        int index = 0;
        while (index < clouds.size() - 1) {
            index = ((index + 2 < clouds.size()) && clouds.get(index + 2) != 1) ? index + 2 : index + 1;

            jumps++;
        }

        return jumps;
    }

    public static long repeatedString(String s, long n) {
        long countA = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == 'a') {
                countA++;
            }
        long total = (n / s.length()) * countA;
        for (int i = 0; i < n - (n / s.length()) * s.length(); i++)
            if (s.charAt(i) == 'a') {
                total++;
            }
        return total;
    }

    public static void checkMagazine(List<String> magazine, List<String> note) {
        if (note == null) {
            System.out.println("No");
            return;
        }
        if (note.size() > magazine.size()) {
            System.out.println("No");
            return;
        }
        HashMap<String, Integer> magazineMap = new HashMap<String, Integer>();
        for (int i = magazine.size() - 1; i >= 0; i--) {
            if (magazineMap.containsKey(magazine.get(i))) {
                magazineMap.put(magazine.get(i), magazineMap.get(magazine.get(i)) + 1);
            } else {
                magazineMap.put(magazine.get(i), 1);
            }
        }
        for (int i = note.size() - 1; i >= 0; i--) {
            if (magazineMap.containsKey(note.get(i))) {
                magazineMap.put(note.get(i), magazineMap.get(note.get(i)) - 1);
                if (magazineMap.get(note.get(i)) < 0) {
                    System.out.println("No");
                    return;
                }
            } else {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    public static String twoStrings(String s1, String s2) {
        String ls = s2.length() > s1.length() ? s2 : s1;
        String ss = s2.length() <= s1.length() ? s2 : s1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ls.length(); i++) {
            map.merge(ls.charAt(i), 1, Integer::sum);
        }
        for (int i = 0; i < ss.length(); i++) {
            if (map.containsKey(ss.charAt(i)))
                return "YES";
        }
        return "NO";
    }

    public static int sherlockAndAnagrams(String str) {
        List<String> subs = new ArrayList<>();
        //list of all substrings
        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                subs.add(str.substring(i, j));
            }
        }
        final Map<String, Integer> map = new HashMap<>();
        subs.forEach(sub -> {
            char[] chars = sub.toCharArray();
            Arrays.sort(chars);
            map.merge(new String(chars), 1, Integer::sum);
        });
        int anagramsCounter = 0;
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (m.getValue() >= 2) {
                anagramsCounter += m.getValue() * (m.getValue() - 1) / 2;
            }
        }
        return anagramsCounter;

    }

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> potential = new HashMap<>();
        Map<Long, Long> counter = new HashMap<>();
        long count = 0;
        for (int i = 0; i < arr.size(); i++) {
            long a = arr.get(i);
            long key = a / r;

            if (counter.containsKey(key) && a % r == 0) {
                count += counter.get(key);
            }

            if (potential.containsKey(key) && a % r == 0) {
                long c = potential.get(key);
                counter.put(a, counter.getOrDefault(a, 0L) + c);
            }

            potential.put(a, potential.getOrDefault(a, 0L) + 1); // Every number can be the start of a triplet.
        }
        return count;
    }





    public static void main(String[] args) {
        System.out.println("triplets = " + countTriplets(Arrays.asList(new Long[]{1l, 2l, 2l, 4l}), 2));
    }
}
