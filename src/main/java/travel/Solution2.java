package travel;

class Solution2 {
    public int solution(int A, int B) {
        int[] aDigits = Integer.toString(A).chars().map(c -> c - '0').toArray();
        int[] bDigits = Integer.toString(B).chars().map(c -> c - '0').toArray();
        int[] cDigits = new int[aDigits.length + bDigits.length];
        int aLen = -1;
        int bLen = -1;
        int cLen = -1;
        while (cLen < (aDigits.length + bDigits.length - 1)) {
            if (aLen < aDigits.length - 1) {
                aLen++;
                cLen++;
                cDigits[cLen] = aDigits[aLen];
            }
            if (bLen < bDigits.length - 1) {
                bLen++;
                cLen++;
                cDigits[cLen] = bDigits[bLen];
            }
        }
        long result = 0;
        for (int i = 0; i <= cLen; i++) {
            result = result * 10 + cDigits[i];
        }
        if (result > 100_000_000) return -1;
        return (int) result;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println("solution2.solution2(12345,678) = " + solution2.solution(12345, 678));
        System.out.println("solution2.solution2(12345,678) = " + solution2.solution(0, 0));
    }
}