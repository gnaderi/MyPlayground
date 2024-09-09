package anotherOne;// you can also use imports, for example:
// import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Replace question mark with random but different from neighbors character.
 */
public class RepalceQuestionMarkCharInString {

    public static void main(String[] args) {
        RepalceQuestionMarkCharInString repalceQuestionMarkCharInString = new RepalceQuestionMarkCharInString();
        String answer = repalceQuestionMarkCharInString.solution("sample?????????????????????????????????????????????????????Test ");
        System.out.println("answer = " + answer);
    }

    public static final String A_TO_Z = "abcdefghijklmnopqrstuvwxyz";
    public static final char QUESTION_MARK = '?';
    public static final char BORDER_CHAR = '$';

    public String solution(String riddle) {

        StringBuilder answer = new StringBuilder(riddle);
        char c;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == QUESTION_MARK) {
                if (i == 0) {
                    c = generateAChar('$', answer.charAt(i + 1));
                } else if (i == answer.length() - 1) {
                    c = generateAChar(answer.charAt(i - 1), BORDER_CHAR);
                } else {
                    c = generateAChar(answer.charAt(i - 1), answer.charAt(i + 1));
                }
                answer.setCharAt(i, c);
            }

        }
        return answer.toString();
    }

    private char generateAChar(char bChar, char fChar) {
        final Map<Integer, Character> charMap = new HashMap<>();
        int size = 0;
        for (int i = 0; i < A_TO_Z.length(); i++) {
            if (A_TO_Z.charAt(i) != bChar && A_TO_Z.charAt(i) != fChar) {
                charMap.put(size, A_TO_Z.charAt(i));
                size++;
            }
        }
        return charMap.get(new Random().nextInt(size));
    }
}
