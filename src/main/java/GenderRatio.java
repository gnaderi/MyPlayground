import java.util.Random;

public class GenderRatio {
    public static int[] runOneFamily() {
        Random random = new Random();
        int boys = 0;
        int girls = 0;
        while (girls == 0) { // until we have a girl
            if (random.nextBoolean()) { // girl
                girls += 1;
            } else { // boy
                boys += 1;
            }
        }
        return new int[]{girls, boys};
    }

    public static double runNFamilies(int n) {
        int boys = 0;
        int girls = 0;
        for (int i = 0; i < n; i++) {
            int[] genders = runOneFamily();
            girls += genders[0];
            boys += genders[1];
        }
        return girls / (double) boys;
    }

    public static void main(String[] args) {
        double ratio = runNFamilies(10000000);
        System.out.println("Female/Male ratio: " + ratio);
    }

}