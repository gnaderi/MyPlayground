package books;

import java.util.*;

public class LongestWord {
    public static void main(String[] args) {
        String[] words = {"able", "ale", "apple", "bale", "kangaroo", "abplee"};
        String keyword = "abppplee";
        String longestWord = findLongestWordInStrings(keyword, words);
        System.out.println("longestWord = " + longestWord);
    }

    private static String findLongestWord(String keyword, String[] words) {
        String longestWord = "";
        for (String word : words) {
            if (word.length() >= keyword.length()) {
                if (word.equals(keyword)) {
                    longestWord = word;
                    break;
                }
                continue;
            }
            int i = 0;
            int j = 0;
            while (i < keyword.length() && j < word.length()) {
                while (j < word.length() && word.charAt(j) == keyword.charAt(i)) {
                    i++;
                    j++;
                }
                i++;
            }
            if (j == word.length()) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
        }
        return longestWord;
    }

    private static String findLongestWord2(String keyword, String[] words) {
        String longestWord = "";
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < keyword.length(); i++) {
            map.computeIfAbsent(keyword.charAt(i), k -> new ArrayList<>()).add(i);
        }
        for (String word : words) {
            int j;
            for (j = 0; j < word.length(); j++) {
                List<Integer> indexes = map.get(word.charAt(j));
                if (indexes == null) {
                    break;
                }
                if (j > indexes.get(0)) {
                    break;
                }
            }
            if (j == word.length()) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }

        }
        return longestWord;
    }

    public static String findLongestWordInStrings(String letters, String[] words) {
        // Corner Case: Either one of them is empty.
        if (letters.isEmpty() || words == null || words.length == 0) {
            return "";
        }
        Map<Character, Set<Pair<String, Integer>>> letterPositions = new HashMap<>();
        // Initialize the data structure. Filter out any empty words that may appear
        // in the word list.
        for (String w : words) {
            if (w.isEmpty()) {
                continue;
            }
            letterPositions.computeIfAbsent(w.charAt(0), k -> new HashSet<>()).add(new Pair<>(w, 0));
        }
        String longestWord = "";
        for (char c : letters.toCharArray()) {
            if (!letterPositions.containsKey(c)) {
                continue;
            }
            Iterator<Pair<String, Integer>> currPositionsIterator = letterPositions.get(c).iterator();

            while (currPositionsIterator.hasNext()) {
                Pair<String, Integer> pair = currPositionsIterator.next();
                // Remove the pair from the set
                currPositionsIterator.remove();
                String currWord = pair.getKey();
                pair.setValue(+1);
                // We found a subsequence.
                if (currWord.length() == pair.getValue()) {
                    if (currWord.length() > longestWord.length()) {
                        longestWord = currWord;
                    }
                } else {
                    letterPositions.computeIfAbsent(currWord.charAt(pair.getValue()), k -> new HashSet<>()).add(pair);
                }
            }

        }
        return longestWord;
    }

    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
