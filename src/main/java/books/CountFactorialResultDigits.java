package books;

public class CountFactorialResultDigits {
    public static void main(String[] args) {
        System.out.println("1000! has " + countFactDigits(1000) + " of digits ");
        System.out.println("101! has " + countFactDigits(101) + " of digits ");
        System.out.println("100! has " + countFactDigits(100) + " of digits ");
        System.out.println(" 50! has " + countFactDigits(50) + " of digits ");
        System.out.println("20! (or " + factorial(20) + ") has " + countFactZeros(20) + " zeros");
        System.out.println("20! has " + countFactDigits(20) + " of digits ");
    }

    public static int countFactDigits(int n) {
        float result = 1;
        for (int i = 1; i <= n; i++) {
            result += (float) Math.log10(i);
        }
        return (int) result;
    }

    public static int countFactZeros(int num) {
        int count = 0;
        if (num < 0) {
            System.out.println("Factorial is not defined for negative numbers");
            return 0;
        }
        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
        }
        return count;
    }

    public static long factorial(int num) {
        if (num == 1) {
            return 1;
        } else if (num > 1) {
            return num * factorial(num - 1);
        } else {
            return -1; // Error
        }
    }
}