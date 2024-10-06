package anotherOne;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class LongestWord {
    public static String LongestWord(String sen) {
        String[] words = sen.split("[^a-zA-Z0-9']+");
        Queue<String> wordsByLen = new PriorityQueue<>((a, b) -> b.length() - a.length());
        wordsByLen.addAll(Arrays.asList(words));
        return wordsByLen.peek();
    }

    public static void main(String[] args) {
        // keep this function call here

        System.out.print(LongestWord("fun&!! time"));
    }

}