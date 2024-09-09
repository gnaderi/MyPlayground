package books;

/*

URLify: Write a method to replace all spaces in a string with '%20'.
You may assume that the string has sufficient space at the end to hold the additional characters, and that you are given the "true" length of the string.
 (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
EXAMPLE
Input: "Mr John Smith      "
Output: "Mr%20John%20smith"
 */
public class SomeStringTasks {
    public static void main(String[] args) {
        System.out.println(new SomeStringTasks().replaceSpaceWithUnicode("Mr  John Smith  ghodrat   naderi                 "));
        System.out.println(new SomeStringTasks().stringCompression("aabcccccccaa"));
        System.out.println(new SomeStringTasks().stringCompression("compressed"));
    }

    public String replaceSpaceWithUnicode(String input) {
        char[] chars = input.toCharArray();
        int l = chars.length;
        for (int i = 0; i < l - 1; i++) {
            if (chars[i] == ' ') {
                for (int j = l - 1; j > i + 2; j--) {
                    chars[j] = chars[j - 1];
                    chars[j - 1] = chars[j - 2];
                    chars[j - 2] = chars[j - 3];
                }
                chars[i] = '%';
                chars[i + 1] = '2';
                chars[i + 2] = '0';
                i += 2;
            }
        }
        return new String(chars);
    }

    public String stringCompression(String input) {
        StringBuilder sb = new StringBuilder();
        int lastCharCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            lastCharCounter++;
            if (i + 1 >= input.length() || (input.charAt(i) != input.charAt(i + 1))) {
                sb.append(input.charAt(i));
                sb.append(lastCharCounter);
                lastCharCounter = 0;
            }
        }

        return sb.length() < input.length() ? sb.toString() : input;
    }

}
