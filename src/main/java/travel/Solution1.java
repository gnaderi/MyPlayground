package travel;

import java.util.*;

class Solution1 {
    int solution(int[] A) {
        int N = A.length;
        int result = 0;
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (hashMap.get(A[i]) == null) {
                hashMap.put(A[i], i);
            } else {
                result = Math.max(result, Math.abs(i - hashMap.get(A[i])));
            }
        }

       /* for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (A[i] == A[j])
                    result = Math.max(result, Math.abs(i - j));*/
        return result;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int A[] = new int[7];
        A[0] = 4;
        A[1] = 6;
        A[2] = 2;
        A[3] = 2;
        A[4] = 6;
        A[5] = 6;
        A[6] = 1;


        int answer = solution1.solution(A);
        System.out.println("answer = " + answer);

        int A2[] = new int[50000];
        for (int i = 0; i < 50000; i++) {
            A2[i] = 2;
        }

        answer = solution1.solution(A2);
        System.out.println("answer = " + answer);


    }
}