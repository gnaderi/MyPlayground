package books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RansomNote {
    public static void checkMagazine(List<String> magazine, List<String> note) {
        if (note == null || magazine.isEmpty() || note.size() > magazine.size()) {
            System.out.println("No");
            return;
        }
        Map<String, Integer> magazineMap = new HashMap<>();
        magazine.forEach(word -> magazineMap.merge(word, 1, Integer::sum));
        for (String word : note) {
            magazineMap.merge(word, -1, Integer::sum);
            if (magazineMap.get(word) < 0) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    public static void main(String[] args) {
        checkMagazine(List.of("ive got a lovely bunch of coconuts".split(" ")), List.of("ive got some coconuts".split(" ")));
        checkMagazine(List.of("give me one grand today night".split(" ")), List.of("give one grand today".split(" ")));
        checkMagazine(List.of("two times three is not four".split(" ")), List.of("two times two is four".split(" ")));


    }
}
