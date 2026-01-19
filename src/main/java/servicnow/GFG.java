package servicnow;

import java.util.List;
import java.util.PriorityQueue;

class GFG {

    /*
    using min heap DS
 
    how data are stored in min Heap DS
           1
         2   3
    if k==3 , then top element of heap
    itself the kth largest largest element
 
    */
    static PriorityQueue<Integer> min;
    static int k;

    static List<Integer> getAllKthNumber(int[] numbers, int k) {

        for (int num : numbers) {

            if (min.size() < k) {
                min.add(num);
                continue;
            }
            if (num > min.peek()) {
                min.poll();
                min.add(num);
            }
        }
        return min.stream().toList();
    }

    // Driver Code
    public static void main(String[] args) {
        min = new PriorityQueue<>();
        k = 10;
        int[] numbers = {11, 2, 3, 4, 51, 6};
        System.out.println("Kth largest elements are: " + getAllKthNumber(numbers, k));

    }
}