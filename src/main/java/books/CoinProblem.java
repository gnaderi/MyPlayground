package books;

import java.util.Arrays;
import java.util.Random;

public class CoinProblem {
    public static void main(String[] args) {
        int[] coins = {1, 3, 5, 10, 20, 50, 100, 200, 500};

        int amount = new Random().nextInt(10000);
        System.out.println("amount = " + amount);
        int[] numberOfCoins = findNumberOfCoins(coins, amount);
        for (int i = 0; i < numberOfCoins.length; i++) {
            if (numberOfCoins[i] > 0) {
                System.out.println(numberOfCoins[i] + " coins of " + coins[i] + "\n");
            }

        }
        System.out.println("Total Number of coins = " + Arrays.stream(numberOfCoins).sum());
    }

    private static int[] findNumberOfCoins(int[] coins, int amount) {
        Arrays.sort(coins);
        int cl = coins.length;
        int[] numberOfCoins = new int[coins.length];
        while (amount > 0) {
            cl--;
            if (amount >= coins[cl]) {
                int quotient = amount / coins[cl];
                amount = amount - coins[cl] * quotient;
                numberOfCoins[cl] = quotient;
            }

        }
        return numberOfCoins;
    }
}