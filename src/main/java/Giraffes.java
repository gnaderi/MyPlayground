import java.util.ArrayList;
import java.util.HashMap;

/**
 * Task description
 * There are N giraffes in your zoo. The zoo is famous for the distinct heights of its giraffes. This means that there are no two giraffes of equal height in the zoo.
 * <p>
 * The giraffes live in displays located along the main path. Every giraffe has its own display. For aesthetic reasons, you want the giraffes to be arranged in ascending order of height. Reordering all the giraffes at once, however, would cause the animals a lot of stress, so you want to split the displays into as many smaller groups as possible and reorder all the groups separately. A group should contain one or more consecutive displays. After reordering all the groups, the giraffes should be arranged in ascending order of height.
 * <p>
 * Write a function:
 * <p>
 * class SolutionSplitArray { public int solution(int[] A); }
 * <p>
 * that, given a zero-indexed array A containing N integers, where A[I] denotes the height of the giraffe in the I-th display, returns the maximum number of groups that can be reordered independently in order to sort all the giraffes in ascending order of height.
 * <p>
 * For example, given A = [1, 5, 4, 9, 8, 7, 12, 13, 14], the function should return 6. Displays can be split into six groups: [1], [5, 4], [9, 8, 7], [12], [13] and [14]. Then, after reordering each group, the whole sequence of giraffes will be sorted into ascending order. Note that group [12, 13, 14] was already ordered correctly, so it could be split into three groups, each containing one giraffe.
 * <p>
 * Given A = [4, 3, 2, 6, 1], the function should return 1. Displays cannot be split into smaller groups; the giraffes have to be sorted all at once.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * the elements of A are all distinct.
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class Giraffes {
        public static boolean checkInclusion(String pattern, String text) {
            if (pattern.length() > text.length())
                return false;
            int[] s1map = new int[26];
            int[] s2map = new int[26];
            for (int i = 0; i < pattern.length(); i++) {
                s1map[pattern.charAt(i) - 'a']++;
                s2map[text.charAt(i) - 'a']++;
            }
            int count = 0;
            for (int i = 0; i < 26; i++)
                if (s1map[i] == s2map[i])
                    count++;
            for (int i = 0; i < text.length() - pattern.length(); i++) {
                int r = text.charAt(i + pattern.length()) - 'a', l = text.charAt(i) - 'a';
                if (count == 26)
                    return true;
                s2map[r]++;
                if (s2map[r] == s1map[r])
                    count++;
                else if (s2map[r] == s1map[r] + 1)
                    count--;
                s2map[l]--;
                if (s2map[l] == s1map[l])
                    count++;
                else if (s2map[l] == s1map[l] - 1)
                    count--;
            }
            return count == 26;
        }

    public static void main(String[] args) {
        String pattern="text";
        String text="afdsaftxetdsfadsf";
        System.out.println("checkInclusion(pattern,text) = " + checkInclusion(pattern, text));
    }

}
