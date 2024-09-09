package books;

import java.util.Arrays;
import java.util.Random;

public class RandomSubSet {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("subSet(new int[]{1,2,3,4,5,6,7,8,9,10},5) = " + Arrays.toString(ranSubSetGenerator(array, 5, array.length - 1)));
    }

    public static <T> T[] ranSubSetGenerator(T[] array, int subSize, int index) {
        T[] subSet = null;
        if (index > subSize) {
            subSet = ranSubSetGenerator(array, subSize, index - 1);
        } else {
            if ((array.length - index) == subSize) {
                return Arrays.copyOfRange(array, 0, subSize);
            }
        }
        int k = new Random().nextInt(0, index + 1);
        if (k < subSize) {
            subSet[k] = array[index];
        }
        return subSet;
    }
}
