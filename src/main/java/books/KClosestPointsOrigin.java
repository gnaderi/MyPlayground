package books;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsOrigin {
    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] kClosest = new KClosestPointsOrigin().kClosest(points, 2);
        System.out.println("kClosest = " + Arrays.toString(kClosest[1]));
    }

    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return null;
        }
        if (points.length < k) {
            return points;
        }
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int[] point : points) {
            pq.offer(new Point(point[0], point[1], Math.sqrt(point[0] * point[0] + point[1] * point[1])));
        }
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point p = pq.poll();
            result[i][0] = p.x;
            result[i][1] = p.y;
        }
        return result;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        double distance;

        public Point(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}
