import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SolutionHHMMSS {
    public static void main(String[] args) {
        SolutionHHMMSS s = new SolutionHHMMSS();
        String result = s.solution(2, 4, 5, 9, 5, 9);
        System.out.println("s = " + result);
    }

    public String solution(int A, int B, int C, int D, int E, int F) {
        Integer validTime[] = new Integer[6];
        List<Integer> highNumbers = new ArrayList<Integer>();
        List<Integer> lowNumbers = new ArrayList<Integer>();
        categoryNumbers(A, B, C, D, E, F, highNumbers, lowNumbers);
        Collections.sort(highNumbers);
        Collections.sort(lowNumbers);
        int hs = highNumbers.size();
        int ls = lowNumbers.size();
        if (hs > ls) {
            return "NOT POSSIBLE";
        } else if (hs == ls) {
            for (int i = 0, v = 0; i < ls; i++, v += 2) {
                validTime[v] = lowNumbers.get(i);
                validTime[v + 1] = highNumbers.get(i);
            }
        } else {
            if (hs == 2 || hs == 1 || hs == 0) {
                for (int i = 0; i < ls; i++) {
                    validTime[i] = lowNumbers.get(i);
                }
                for (int i = 0; i < hs; i++) {
                    validTime[i + ls] = highNumbers.get(i);
                }
                if (hs == 2) {
                    int temp = validTime[3];
                    validTime[3] = validTime[4];
                    validTime[4] = temp;
                    if(validTime[0]==2 &&validTime[1]==4){
                        return "NOT POSSIBLE";
                    }
                }
            }
        }
        StringBuilder earlyTime = new StringBuilder();
        for (int i = 0; i < validTime.length; i += 2) {
            earlyTime.append(validTime[i]).append(validTime[i + 1]).append(":");
        }
        return earlyTime.substring(0, earlyTime.length() - 1).toString();
    }

    private void categoryNumbers(int A, int B, int C, int D, int E, int F, List<Integer> highNumbers, List<Integer> lowNumbers) {
        if (A > 5) {
            highNumbers.add(A);
        } else {
            lowNumbers.add(A);
        }
        if (B > 5) {
            highNumbers.add(B);
        } else {
            lowNumbers.add(B);
        }
        if (C > 5) {
            highNumbers.add(C);
        } else {
            lowNumbers.add(C);
        }
        if (D > 5) {
            highNumbers.add(D);
        } else {
            lowNumbers.add(D);
        }
        if (E > 5) {
            highNumbers.add(E);
        } else {
            lowNumbers.add(E);
        }
        if (F > 5) {
            highNumbers.add(F);
        } else {
            lowNumbers.add(F);
        }
    }
}