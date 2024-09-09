package books;

public class BitManipulation {
    public static void main(String[] args) {
        System.out.println("flipBit(1775, 8) = " + flipBit(1775));
        System.out.println("nextGreaterNumber(8) = " + nextGreaterNumber(8));
    }

    public static int flipBit(int n) {
        char[] bits = Integer.toBinaryString(n).toCharArray();
        int bitString = bits[1] == '1' ? 1 : 0;
        int longestBitString = bitString;
        boolean flipped = false;
        int flipIndex = 0;
        for (int i = 1; i < bits.length; i++) {
            if (bits[i] == '0') {
                if (!flipped) {
                    flipped = true;
                    bitString = bitString + 1;
                    flipIndex = i;
                } else {
                    longestBitString = Math.max(longestBitString, bitString);
                    bitString = 0;
                    flipped = false;
                    i = flipIndex;
                }
            }
            if (bits[i] == '1') {
                bitString = bitString + 1;
            }
        }
        return Math.max(longestBitString, bitString);
    }

    public static int nextGreaterNumber(int num) {
        int rightOne, nextHigherOneBit, rightOnesPattern, next = 0;

        if(num > 0)
        {

            // right most set bit
            rightOne = num & -num;

            // reset the pattern and set next higher bit
            // left part of num will be here
            nextHigherOneBit = num + rightOne;

            // nextHigherOneBit is now part [D] of the above explanation.

            // isolate the pattern
            rightOnesPattern = num ^ nextHigherOneBit;

            // right adjust pattern
            rightOnesPattern = (rightOnesPattern)/rightOne;

            // correction factor
            rightOnesPattern >>= 2;

            // rightOnesPattern is now part [A] of the above explanation.

            // integrate new pattern (Add [D] and [A])
            next = nextHigherOneBit | rightOnesPattern;
        }

        return next;
    }
}
