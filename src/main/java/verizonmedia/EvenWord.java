package verizonmedia;

import java.util.HashMap;
import java.util.Map;

/*

Given that an "even word" is a word in which each character appears an even number of times, write a function that takes in a string and returns the minimum number of letters to be removed to make that string an even word.

Example:
evenWord('aaaa')
>0

evenWord('potato')
>2

 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class EvenWord {

    private static int evenWord(String input) {
        Map<Character, Integer> counterMap = new HashMap<>(input.length());

        for (int i = 0; i < input.length(); i++) {
            Integer value = counterMap.get((Character) input.charAt(i));

            if (value != null) {
                counterMap.put((Character) input.charAt(i), ++value);

            } else {
                counterMap.put((Character) input.charAt(i), 1);
            }
        }

        int minNumLetter = 0;
        Integer[] values = counterMap.values().toArray(new Integer[0]);
        for (int i = 0; i < values.length; i++) {
            if (values[i] % 2 != 0) {
                minNumLetter++;
            }
        }
        return minNumLetter;

    }

    public static void main(String[] args) {
        System.out.println("2=" + evenWord("potato"));
    }
}