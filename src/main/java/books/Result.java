package books;

class Result {

    /*
     * Complete the 'fizzBuzz' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void fizzBuzz(int n) {
        Thread[] threads = {new FBThread(i -> i % 3 == 0 && i % 5 == 0, i -> "FizzBuzz", n),
                new FBThread(i -> i % 3 == 0 && i % 5 != 0, i -> "Fizz", n),
                new FBThread(i -> i % 3 != 0 && i % 5 == 0, i -> "Buzz", n),
                new FBThread(i -> i % 3 != 0 && i % 5 != 0, i -> Integer.toString(i), n)};
        for (Thread thread : threads) {
            thread.start();
        }

    }

    public static void main(String[] args) {
        fizzBuzz(100);
    }

}