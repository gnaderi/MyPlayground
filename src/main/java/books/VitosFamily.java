package books;

import java.util.Arrays;

/**
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=12&page=show_problem&problem=982
 */
public class VitosFamily {
    public static void main(String[] args) {
        int[] streetNos = {2, 2, 4, 3, 2, 4, 6};
        int streetNo = new VitosFamily().bestHouseLocationForVito2(streetNos);
        System.out.println(streetNo);
    }

    public int bestHouseLocationForVito(int[] streetNumbers) {
        if (streetNumbers == null || streetNumbers.length == 0) {
            return 0;
        }
        Arrays.sort(streetNumbers);
        int mid = streetNumbers.length / 2;
        int median = streetNumbers[mid];
        int distanceFronMightyVitoHouse = 0;
        for (int i = 0; i < streetNumbers.length; i++) {
            distanceFronMightyVitoHouse += Math.abs(median - streetNumbers[i]);
        }

        return distanceFronMightyVitoHouse / 2;
    }

    public int bestHouseLocationForVito2(int[] streetNumbers) {
        if (streetNumbers == null || streetNumbers.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < streetNumbers.length; i++) {
            min = Math.min(min, streetNumbers[i]);
            max = Math.max(max, streetNumbers[i]);
        }
        int median = min + (max - min) / 2;
        int distanceFronMightyVitoHouse = 0;
        for (int i = 0; i < streetNumbers.length; i++) {
            distanceFronMightyVitoHouse += Math.abs(median - streetNumbers[i]);
        }

        return distanceFronMightyVitoHouse / 2;
    }
    public int bestHouseLocationForVito3(int[] streetNumbers) {
        if (streetNumbers == null || streetNumbers.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < streetNumbers.length; i++) {
            min = Math.min(min, streetNumbers[i]);
            max = Math.max(max, streetNumbers[i]);
        }
        int median = min + (max - min) / 2;
        int distanceFronMightyVitoHouse = 0;
        for (int i = 0; i < streetNumbers.length; i++) {
            distanceFronMightyVitoHouse += Math.abs(median - streetNumbers[i]);
        }

        return distanceFronMightyVitoHouse / 2;
    }
}
