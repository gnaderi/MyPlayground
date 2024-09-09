package books;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MoveZeroes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZeroes(new int[]{0, 1, 0, 2, 3, 0, 4, 0, 5, 6, 7, 8, 0, 9, 0})));

        System.out.println(Arrays.toString(moveZeroes(new int[]{1, 2, 3, 4, 0, 1, 0})));
        System.out.println(Arrays.toString(findArrayQuadruplet(new int[]{2, 7, 4, 0, 9, 5, 1, 3}, 20)));
    }

    private static int[] moveZeroes(int[] array) {
        int fillingIndex = 0;
        for (int num : array) {
            if (num != 0) {
                array[fillingIndex] = num;
                fillingIndex++;
            }
        }
        for (int j = fillingIndex; j < array.length; j++) {
            array[j] = 0;
        }
        return array;
    }

    private static int[] findArrayQuadruplet(int[] array, int target) {
        int[] answer = new int[4];

        Map<Integer, Integer> mapIJ = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                mapIJ.put(Math.abs(array[i] + array[j]), i);
            }
        }
        for (int num : array) {
            if (mapIJ.containsKey(target - num)) {
                answer[0] = array[mapIJ.get(Math.abs(target - num))];
                answer[1] = num;
                answer[2] = target - answer[0] - num;
                answer[3] = target - answer[0] - answer[1] - answer[2];
            }
        }
        Arrays.sort(answer);
        return answer;
    }
}
