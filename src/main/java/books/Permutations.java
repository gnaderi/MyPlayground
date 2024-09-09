package books;

public class Permutations {

    private final String in;
    private boolean[] used;
    private StringBuilder out = new StringBuilder();

    public Permutations(final String str) {
        in = str;
        used = new boolean[in.length()];
    }

    public static void main(String[] args) {
//        new Permutations("hargodt").permute();
        new Permutations("hargodt").permute2("abc", "");
    }

    public void permute() {
        if (out.length() == in.length()) {
            System.out.println(out);
            return;
        }
        for (int i = 0; i < in.length(); ++i) {
            if (used[i]) continue;
            out.append(in.charAt(i));
            used[i] = true;
            permute();
            used[i] = false;
            out.setLength(out.length() - 1);
        }
    }

    public void permute2(String str, String prefix) {
        int n = str.length();
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute2(str.substring(0, i) + str.substring(i + 1, n), prefix + str.charAt(i));
            }
        }
    }
}