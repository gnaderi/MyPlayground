package optum;

import java.util.*;
import java.util.stream.Stream;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("solution. = " + solution.solution(3, 2));
    }
    public static final String A_TO_Z = "abcdefghijklmnopqrstuvwxyz";

    public String solution(int N, int K) {
        Map<Integer, Character> map = generateDistinctCharsMap(K);
        char[] word = new char[N];
        for (int i = 0; i < N / 2; i++) {
            word[i] = getRandomChar(map,Arrays.toString(word),K);
        }
        if (isEven(N)) {
            for (int i = N - 1; i >= N / 2; i--) {
                word[i] = word[N - i - 1];
            }
        } else {
            word[N / 2] = getRandomChar(map,Arrays.toString(word),K);
            for (int i = N - 1; i > N / 2; i--) {
                word[i] = word[N - i - 1];
            }
        }
        return new String(word);
    }

    boolean isEven(int number) {
        return number % 2 == 0;
    }

    private Character getRandomChar(Map<Integer, Character> map, String word, int K) {
        Optional<Character> any = map.values().stream().filter(v -> !word.contains(String.valueOf(v))).findAny();

        return any.orElseGet(() -> map.get(new Random().nextInt(K)));

    }

    private Map<Integer, Character> generateDistinctCharsMap(int listSize) {
        List<Character> alphabets = new ArrayList<>();
        for (char ch : A_TO_Z.toCharArray()) {
            alphabets.add(ch);
        }
        final Map<Integer, Character> selectedCharsMap = new HashMap<>();
        for (int i = 0; i < listSize; i++) {
            int index = new Random().nextInt(alphabets.size());
            Character selectedChar = alphabets.get(index);
            alphabets.remove(selectedChar);
            selectedCharsMap.put(i, selectedChar);
        }
        return selectedCharsMap;
    }
}
