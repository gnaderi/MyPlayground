package books;

public class SqrtBinarySearch {
    public static void main(String[] args) {
        int n = 4;
        int root = sqrtBS(n);
        System.out.println("root = " + root);
    }

    private static int sqrtBS(int n) {
        int left = 1, right = n;
        //≈
        int closetSqrt = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sqMid = mid * mid;
            if (sqMid == n) {
                return mid;
            }
            if (sqMid < n) {
                left = mid + 1;
                closetSqrt = mid;
            } else {
                right = mid - 1;
            }


        }
        return closetSqrt;
    }
}
