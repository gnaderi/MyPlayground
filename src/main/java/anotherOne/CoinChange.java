package anotherOne;

import java.util.Arrays;
import java.util.Random;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {186, 419, 83, 408};
        int amount = new Random().nextInt(10000);
        int coinsCount = 0;
        System.out.println("amount = " + amount);
        int[] numberOfCoins = findNumberOfCoins(coins, 6249);
        for (int i = 0; i < numberOfCoins.length; i++) {
            if (numberOfCoins[i] > 0) {
                System.out.println("coins= " + coins[i] + " Count=" + numberOfCoins[i] + "\n");
                coinsCount += numberOfCoins[i];
            }

        }
        System.out.println("numberOfCoins = " + coinsCount);
    }

    private static int[] findNumberOfCoins(int[] coins, int amount) {
        Arrays.sort(coins);
        int c = coins.length;
        int[] numberOfCoins = new int[coins.length];
        while (amount > 0) {
            c--;
            if (amount >= coins[c]) {
                int quotient = amount / coins[c];
                amount = amount - coins[c] * quotient;
                numberOfCoins[c] = quotient;
            }

        }
        return numberOfCoins;
    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int c = coins.length;
        int numberOfCoins = 0;
        while (amount > 0) {
            if (c > 0) {
                c--;
            } else {
                return -1;
            }

            if (amount >= coins[c]) {
                int quotient = amount / coins[c];
                amount = amount - coins[c] * quotient;
                numberOfCoins += quotient;
            }

        }
        return numberOfCoins;
    }
}
