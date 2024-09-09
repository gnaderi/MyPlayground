package books;

public class Combinations {
    private final String in;
    private StringBuilder out = new StringBuilder();

    public Combinations(final String str) {
        in = str;
    }

    public static void main(String[] args) {
        new Combinations("123").combine();

        System.out.println("waterbottle\"is a rotation of\"erbottlewat\": " + areRotations("waterbottle", "erbottlewat"));
    }

    private static boolean areRotations(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        String str = str1 + str1;
        for (int i = 0; i < str.length(); i++) {
            int j = 0;
            while (str.charAt(i + j) == str2.charAt(j) && ++j < str2.length()) ;
            if (j >= str2.length()) {
                return true;
            }
        }
        return false;
    }

    public void combine() {
        combine(0);
    }

    private void combine(int start) {
        for (int i = start; i < in.length(); ++i) {
            out.append(in.charAt(i));
            System.out.println(out);
            combine(i + 1);
            out.setLength(out.length() - 1);
        }
    }
}
