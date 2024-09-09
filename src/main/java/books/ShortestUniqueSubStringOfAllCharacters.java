package books;

import java.util.Arrays;

public class ShortestUniqueSubStringOfAllCharacters {
    public static void main(String[] args) {
        ShortestUniqueSubStringOfAllCharacters instance = new ShortestUniqueSubStringOfAllCharacters();

        System.out.println("instance = " + instance.getShortestUniqueSubstring_Big_O_N_charsLen(new char[]{'x', 'y', 'z'}, "xyyzyzyx"));

    }

    public String getShortestUniqueSubstring_Big_O_N_charsLen(char[] chars, String str) {
        int[] tracker = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == str.charAt(i)) {
                    tracker[i]++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < tracker.length; i++) {
            if (tracker[i] < min) {
                minIndex = i;
            }
        }
        return minIndex == -1 ? "" : str.substring(minIndex, tracker[minIndex]);
    }

    public String getShortestUniqueSubstring(char[] chars, String str) {
        Arrays.sort(chars);
        for (int i = 0; i < str.length() - chars.length; i++) {
            char[] subChars = str.substring(i, i + chars.length).toCharArray();
            if (isSame(chars, subChars)) {
                return new String(subChars);
            }
        }
        return "";
    }

    private boolean isSame(char[] chars, char[] subChars) {
        for (int i = 0; i < subChars.length; i++) {
            int j = 0;
            while (j < chars.length && chars[i] != subChars[j]) {
                j++;
            }
            if (j == chars.length) {
                return false;
            }
        }
        return true;
    }
}

