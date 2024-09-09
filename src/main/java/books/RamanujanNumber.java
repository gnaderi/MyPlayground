package books;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RamanujanNumber {
    public static void main(String[] args) {
        Map<Integer, RajNum> map = new RamanujanNumber().generateAllRamanujan(1000);
        for (Map.Entry<Integer, RajNum> e : map.entrySet()) {
            System.out.println(e.getKey() + " : a:" + e.getValue().a + " b:" + e.getValue().b + " c:" + e.getValue().c + " d:" + e.getValue().d);
        }


        Map<Integer, ThreeOfCube3Nub> cube3NubMap = new RamanujanNumber().generate3OfCube3(44);
        for (Map.Entry<Integer, ThreeOfCube3Nub> e : cube3NubMap.entrySet()) {
            System.out.println(e.getKey() + " : a:" + e.getValue().a + " b:" + e.getValue().b + " c:" + e.getValue().c + " d:" + e.getValue().d
                    + " e:" + e.getValue().e + " f:" + e.getValue().f);
        }
    }

    Map<Integer, RajNum> generateAllRamanujan(int n) {
        int[] cubes = new int[n];
        for (int i = 1; i < n; i++) {
            cubes[i] = i * i * i;
        }
        Map<Integer, RajNum> sumPairsMap = new HashMap<>();
        Map<Integer, RajNum> ramnujanNumsMap = new TreeMap<>();
        for (int a = 1; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                int a3 = cubes[a];
                int b3 = cubes[b];
                int sumPairs = a3 + b3;
                if (sumPairsMap.containsKey(sumPairs)) {
                    int c = sumPairsMap.get(sumPairs).a;
                    int d = sumPairsMap.get(sumPairs).b;
                    ramnujanNumsMap.put(sumPairs, new RajNum(a, b, c, d));
                } else {
                    sumPairsMap.put(sumPairs, new RajNum(a, b, -1, -1));
                }
            }
        }
        return ramnujanNumsMap;
    }

    Map<Integer, ThreeOfCube3Nub> generate3OfCube3(int n) {
        int[] cubes = new int[n + 1];
        for (int i = 1; i < n; i++) {
            cubes[i] = i * i * i;
        }
        Map<Integer, ThreeOfCube3Nub> sumPairsMap = new HashMap<>();
        Map<Integer, ThreeOfCube3Nub> cube3Of3Map = new TreeMap<>();
        for (int a = 1; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; b < n; b++) {
                    int a3 = cubes[a];
                    int b3 = cubes[b];
                    int c3 = cubes[c];
                    int sumPairs = a3 + b3 + c3;
                    if (sumPairsMap.containsKey(sumPairs)) {
                        cube3Of3Map.put(sumPairs, new ThreeOfCube3Nub(sumPairsMap.get(sumPairs).a, sumPairsMap.get(sumPairs).b,
                                sumPairsMap.get(sumPairs).c, a, b, c));
                    } else {
                        sumPairsMap.put(sumPairs, new ThreeOfCube3Nub(a, b, c, -1, -1, -1));
                    }
                }
            }
        }
        return cube3Of3Map;
    }

    class RajNum {
        public int a;
        public int b;
        public int c;
        public int d;

        public RajNum(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }

    class ThreeOfCube3Nub {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;

        public ThreeOfCube3Nub(int a, int b, int c, int d, int e, int f) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }
    }
}
