package books;

/*

Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.

 */
public class T1Dot2 {
    public static void main(String[] args) {
        System.out.println("isPermutedOfEachOther(\"ghodrat\", \"tardohg\") = " + new T1Dot2().isPermutedOfEachOther("22", "31"));
        System.out.println("isPermutedOfEachOther(\"ghodrat\", \"tardohg\") = " + new T1Dot2().isPermutedOfEachOther2("22", "31"));
    }

    private static long calculateHash(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = 31 * result + str.charAt(i);
        }
        return result;
    }

    //O(n)
    private boolean isPermutedOfEachOther(String first, String second) {
        int result = 0;
        for (int i = 0; i < first.length(); i++) {
            result ^= first.charAt(i) ^ second.charAt(i);
        }
        return result == 0;
    }

    //O(n)
    private boolean isPermutedOfEachOther2(String first, String second) {
        return calculateHash(first) == calculateHash(second);
    }

}
