package books.playerboard;

import java.util.*;

/**
 * There are n customer requests placed sequentially in a queue, where the ith request has a maximum waiting time denoted by wait[i].
 * That is, if the ith request is not served within wait[i] seconds, then the request expires and it is removed from the queue.
 * The server processes the request following the First In First Out (FIFO) principle.
 * The 1st request is processed first, and the nth request is served last.
 * At each second, the first request in the queue is processed. At the next second, the processed request and any expired requests are removed from the queue.
 * <p>
 * Given the maximum waiting time of each request denoted by the array wait, find the number of requests present in the queue at every second until it is empty.
 * <p>
 * Note:
 * <p>
 * If a request is served at some time instant t, it will be counted for that instant and is removed at the next instant.
 * The first request is processed at time = 0. A request expires without being processed when time = wait[i]. It must be processed while time < wait[i].
 * The initial queue represents all requests at time = 0 in the order they must be processed.
 * Function Description
 * <p>
 * Complete the function findRequestsInQueue in the editor.
 * <p>
 * findRequestsInQueue has the following parameter:
 * <p>
 * int wait[n]: the maximum waiting time of each request
 * Returns
 * <p>
 * int[]: the number of requests in the queue at each instant until the queue becomes empty.
 * <p>
 * Example 1:
 * <p>
 * Input: wait = [2, 2, 3, 1]
 * Output: [4, 2, 1, 0]
 */
public class HackerRank {
    public static void main(String[] args) {
        System.out.println("requestsInQueue( [4, 2, 1, 0]) = " + findRequestsInQueue2(List.of(2, 2, 3, 1)));
        System.out.println("requestsInQueue( [3, 2, 1, 0]) = " + findRequestsInQueue2(List.of(4, 4, 4)));
        System.out.println("requestsInQueue( [4, 1, 0]) = " + findRequestsInQueue2(List.of(3, 1, 2, 1)));
    }

    public static List<Integer> findRequestsInQueue2(List<Integer> wait) {
        Queue<Integer> queue = new LinkedList<>();
        for (Integer w : wait) {
            queue.offer(w);
        }
        int time = 0;
        List<Integer> requestsInQueue = new ArrayList<>();
        requestsInQueue.add(queue.size());
        time++;
        while (!queue.isEmpty()) {
            queue.poll();
            final int expiredTime = time;
            queue.removeIf(w -> w <= expiredTime);
            requestsInQueue.add(queue.size());
            time++;
        }
        return requestsInQueue;
    }

    public static List<Integer> findRequestsInQueue(List<Integer> wait) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        for (int w : wait) {
            minHeap.offer(w);
        }
        int time = 0;
        while (!minHeap.isEmpty()) {
            while (!minHeap.isEmpty() && minHeap.peek() <= time) {
                minHeap.poll();
            }
            result.add(minHeap.size());
            minHeap.remove(wait.get(time));
            time++;
        }
        if (!result.contains(0)) {
            result.add(0);
        }
        return result;
    }
}
