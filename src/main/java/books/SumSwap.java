package books;

public class SumSwap {
    public static void main(String[] args) {
        sumSwap(new int[]{4, 1, 2, 1, 1, 2}, new int[]{3, 6, 3, 3});
    }

    public static void sumSwap(int[] firstArray, int[] secondArray) {
        long sumFirst = 0;
        for (int j : firstArray) {
            sumFirst += j;
        }
        long sumSecond = 0;
        for (int j : secondArray) {
            sumSecond += j;
        }
        long target = Math.abs(sumSecond - sumFirst) / 2;//a-b
        for (int j = 0; j < secondArray.length; j++) {
            for (int k = 0; k < firstArray.length; k++) {
                if (Math.abs(firstArray[k] - secondArray[j]) == target) {
                    System.out.println("First = " + firstArray[k]);
                    System.out.println("Second = " + secondArray[j]);
                    return;
                }
            }
        }
    }
}
