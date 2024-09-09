package books;

import java.util.HashSet;
import java.util.Set;

/*
Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
 */
public class T1Dot1 {
    public static void main(String[] args) {
        System.out.println("new T1Dot1().isUnique(\"ghodrat\") = " + new T1Dot1().isMadeOfUniqueChars("ghodrat"));
        System.out.println("new T1Dot1().isUnique(\"naderi\") = " + new T1Dot1().isMadeOfUniqueChars("naderi"));
        System.out.println("new T1Dot1().isUnique(\"ghodrat naderi\") = " + new T1Dot1().isMadeOfUniqueChars("ghodrat nAdERi"));
    }

    //O(n)
    private boolean isUnique(String str) {
        int[] chars = new int[256];
        for (int i = 0; i < str.length(); i++) {
            chars[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (chars[str.charAt(i)] > 1) {
                return false;
            }
        }
        return true;
    }

    //O(n)
    private boolean isMadeOfUniqueChars(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }
}
