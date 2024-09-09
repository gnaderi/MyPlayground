import java.util.Stack;

/**
 * Check if the braces are balanced.
 */
public class BraceChecker {
    public static void main(String[] args) {
        BraceChecker braceChecker = new BraceChecker();
        System.out.println("true = " + braceChecker.isValid("(){}[]"));
        System.out.println("true = " + braceChecker.isValid("([{}])"));
        System.out.println("false = " + braceChecker.isValid("(}"));
        System.out.println("false = " + braceChecker.isValid("[(])"));
    }

    public boolean isValid(String braces) {
        Stack<Character> stack = new Stack<>();
        boolean valid = true;
        try {

            for (int i = 0; i < braces.length(); i++) {
                if (braces.charAt(i) == '[' || braces.charAt(i) == '{' || braces.charAt(i) == '(') {
                    stack.push(braces.charAt(i));
                } else {
                    Character pop = stack.pop();
                    char closeBrace = braces.charAt(i);
                    valid = switch (pop) {
                        case '{' -> closeBrace == '}';
                        case '[' -> closeBrace == ']';
                        case '(' -> closeBrace == ')';
                        default -> true;
                    };
                    if (!valid) {
                        return false;
                    }
                }

            }
        } catch (Exception ex) {
            return false;
        }
        return stack.isEmpty();
    }
}