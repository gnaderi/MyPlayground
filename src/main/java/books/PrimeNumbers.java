package books;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {
    public static void main(String[] args) {
        List<Integer> integers = sieveOfEratosthenes(1_000_000);
        System.out.println("AllPrimeNumbersUpTo(n) = " + integers);
        System.out.println("AllPrimeNumbersUpTo(n) = " + integers.size());
    }
    static List<Integer> sieveOfEratosthenes(int max) {
        boolean[] flags = new boolean[max + 1];
        // Set all flags to true other than 0 and 1
        flags[0] = false;
        flags[1] = false;
        for (int i = 2; i <= max; i++) {
            flags[i] = true;
        }
        int prime = 2;
        while (prime <= Math.sqrt(max)) {
            /* Cross off remaining multiples of prime */
            crossOff(flags, prime);
            /* Find next value which is true */
            prime = getNextPrime(flags, prime);
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            if (flags[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    static void crossOff(boolean[] flags, int prime) {
        /* Cross off remaining multiples of prime. We can start with (prime*prime),
         * because if we have a k * prime, where k < prime, this value would have
         * already been crossed off in a prior iteration. */
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    static int getNextPrime(boolean[] flags, int prime) {
        int next = prime + 1;
        while (next < flags.length && !flags[next]) {
            next++;
        }
        return next;
    }

}
