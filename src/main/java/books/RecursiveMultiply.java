package books;

public class RecursiveMultiply {
    public static void main(String[] args) {
        System.out.println("7*8 = " + multiply(9, 8));
        System.out.println("8^4(4K) = " + power(8, 4));
        System.out.println("log(100) = " + logN(100));
    }

    public static long multiply(int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);
        return helper(smaller, bigger);
    }

    private static long helper(int smaller, int bigger) {
        if (smaller == 0) {
            return 0;
        } else if (smaller == 1) {
            return bigger;
        }
        int s = smaller >> 1;
        long side1 = multiply(s, bigger);
        if (smaller % 2 == 0) {
            return side1 + side1;
        }
        return side1 + side1 + bigger;
    }
    /*
    power of a*b in log(n)
     */

    public static long power(int base, int power) {
        if (power == 0) {
            return 1;
        }
        long x = power(base, power >> 1);
        long xPowerOfTwo = x * x;
        if (power % 2 == 0) {
            return xPowerOfTwo;
        }
        return base * xPowerOfTwo;
    }

    public static double logN(int n) {
        double log = 0;
        for (int i = 1; i <= n; i++) {
            log += 1.00 / i;
        }
        return log;
    }

}
