import java.util.concurrent.ConcurrentHashMap;

public class kthGreatest {

    public static void main(String[] args) {
        int a[] = {1, 5, 15, 20,26,36,50};
        int b[] = {3, 6, 16, 17,35,39};
        int k =3;
        int result = kthGreatest(a, b, k);
        System.out.println("result = " + result);
    }

    private static int kthGreatest(int[] a, int[] b, int k) {
        int ai = 1;
        int bi = 1;
        int al = a.length;
        int bl = b.length;
        for (int i = 1; i < k; i++) {
            if (a[al - ai] > b[bl - bi]) {
                ai++;
            } else if (a[al - ai] < b[bl - bi]) {
                bi++;
            } else {
                ai++;
                bi++;
            }
        }
        return a[al - ai] >= b[bl - bi] ? a[al - ai] : b[bl - bi];
    }
}