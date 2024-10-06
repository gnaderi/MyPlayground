package anotherOne;

import java.util.Arrays;

public class CoinChange2 {
    public static void main(String[] args) {
        int[] coins = {186, 419, 83, 408};

        int numberOfCoins = minCoinChange(coins, 6249);

        System.out.println("numberOfCoins = " + numberOfCoins);
    }


    public static int minCoinChange(int[] coins, int amount) {
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
