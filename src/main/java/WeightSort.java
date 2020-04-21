import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Sort the string of number based on sum of their digits.
 */
public class WeightSort {

    public static String orderWeight(String strng) {
        // your code
        String[] srtNumbers = strng.split(" ");
        String sortedString = Arrays.stream(srtNumbers).sorted(Comparator.comparingInt(WeightSort::sumDigit).thenComparing(o -> o)).collect(Collectors.joining(" "));

        return sortedString;
    }

    private static Integer sumDigit(String number) {
        Integer sum = 0;
        for (char c : number.toCharArray()) {
            sum += c - '0';
        }
        return sum;
    }
}