package books;

public class AllCombinations {
    public static void main(String[] args) {
        allPermute("", "ghodrat");
    }

    public static void allPermute(String prefix, String word) {
        if (word.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < word.length(); i++) {
                String rem = word.substring(0, i) + word.substring(i + 1);
                allPermute(prefix + word.charAt(i), rem);
            }
        }
    }
}
