package books;

public class AddSubNoSign {
    public static int add(int a, int b) {
        if (b == 0) return a;
        int sum = a ^ b; // add without carrying
        int carry = (a & b) << 1; // carry, but don�t add
        return add(sum, carry); // recurse
    }

    public static int sub(int a, int b) {
        if (b == 0) return a;
        int diff = a ^ b;
        int carry = (~a & b) << 1;
        return sub(diff, carry);
    }

    public static long multiply(int n, int m) {
        long result = 0, count = 0;
        while (m > 0) {
            if (m % 2 == 1)
                result += n << count;
            count++;
            m /= 2;
        }

        return result;
    }

    public static void main(String[] args) {
        int a = 90;
        int b = 50;
        int sum = add(a, b);
        int sub = sub(a, b);
        long multiply = multiply(a, b);
        System.out.println(a + " + " + b + " = " + sum);
        System.out.println(a + " - " + b + " = " + sub);
        System.out.println(a + " * " + b + " = " + multiply);
    }

}
