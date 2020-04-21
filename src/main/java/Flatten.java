import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;

public class Flatten {

    public static void main(String[] args) {
        //[[1,2],3,[4]]
        List<Integer> a12 = new ArrayList<>();
        a12.add(1);
        a12.add(2);

        List<Object> a1 = new ArrayList();
        a1.add(a12);

        List<Object> l = new ArrayList<>();
        l.add(a1);
        l.add(3);
        List<Integer> a13 = new ArrayList<>();
        a13.add(4);
        l.add(a13);

        Flatten flatten = new Flatten();
        List<Integer> flattedList = flatten.flattenList(l);
        System.out.print("[");
        for (int i = 0; i < flattedList.size() - 1; i++) {
            System.out.print(flattedList.get(i) + ",");
        }
        System.out.println(flattedList.get(flattedList.size() - 1) + "]");
        //[1,2,3,4]
    }

    /**
     * Receive a list of un even array of  elements in list
     *
     * @param input Received List<Objects>
     * @return List<Integer> of flatten Integer values in List
     */
    public List<Integer> flattenList(List input) {
        List<Integer> output = new ArrayList<>();
        flatten(input, output);
        return output;
    }

    /**
     * Recurrently call to flatten all elements of the main list.
     * @param input
     * @param output
     */
    private void flatten(List input, List<Integer> output) {
        for (Object o : input) {
            if (o instanceof List) {
                flatten((List) o, output);
            } else if (o instanceof Integer) {
                output.add((Integer) o);
            } else {
                throw new UnknownFormatConversionException("Unsupported element data type in input list.");
            }
        }
    }
}