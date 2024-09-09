package books;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class KLargestElements {
    public static void main(String[] args) {
        int[] randomArray = {11, 2, -3, 4, 15, -6, 7, 1, 19, 2, 52, 6, 12, 9, 0, 123, -4, 9, 56};
        int[] kLargestElements = findKLargest(randomArray, 3);
        System.out.println("findKLargest = " + Arrays.toString(kLargestElements));
        Integer[] kLargests = findKLargest_BigO_NK(randomArray, 3);
        System.out.println("findKLargest_BigO_NK = " + Arrays.toString(kLargests));
        kLargests = findKLargestWithExtraArray(randomArray, 3);
        System.out.println("findKLargestWithExtraArray = " + Arrays.toString(kLargests));
    }

    private static int[] findKLargest(int[] numbs, int k) {
        if (k <= 0 || k > numbs.length) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int numb : numbs) {
            pq.offer(numb);
        }
        return IntStream.range(0, k ).map(i -> pq.poll()).toArray();
    }

    private static Integer[] findKLargestWithExtraArray(int[] numbs, int k) {
        if (k <= 0 || k > numbs.length) {
            throw new IllegalArgumentException();
        }
        int[] clone = numbs.clone();
        Arrays.sort(clone);
        Integer[] largest = new Integer[k];
        for (int i = 0; i < k; i++) {
            largest[i] = clone[clone.length - i - 1];
        }
        return largest;
    }

    private static Integer[] findKLargest_BigO_NK(int[] numbs, int k) {
        if (k <= 0 || k > numbs.length) {
            throw new IllegalArgumentException();
        }
        int min = Integer.MAX_VALUE;
        for (Integer numb : numbs) {
            min = Math.min(min, numb);
        }
        Integer[] largest = new Integer[k];
        Arrays.fill(largest, min - 1);
        for (Integer numb : numbs) {
            int index = 0;
            while (index < k && numb < largest[index]) index++;
            if (index < k) {
                int temp = largest[index];
                largest[index] = numb;
                for (int j = index + 1; j < largest.length; j++) {
                    int temp2 = largest[j];
                    largest[j] = temp;
                    temp = temp2;
                }
            }
        }
        return largest;
    }

}
