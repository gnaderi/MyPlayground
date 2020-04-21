package palindrome;

import java.util.Arrays;

public class LongestComonStringInArrayOfStrings {

    public String longestCommonPrefix(String[] strs) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {

            smallest = Math.min(strs[i].length(), smallest);
        }

        for (int i = 0; i < smallest; i++) {

            for (int j = 0; j < strs.length-1; j++) {
                int result = strs[j].charAt(i) ^ strs[j + 1].charAt(i);
                if(result!=0){
                    return i>0?strs[0].substring(0,i):null;
                }
            }
        }
        return strs[0].substring(0,smallest);
    }

    public static void main(String[] args) {
        System.out.println("T1 = " + new LongestComonStringInArrayOfStrings().longestCommonPrefix(new String[]{"abccdedcb", "abccefc","abccdedfdsfadsfdsafcb", "abcc000000000000000000000000000000000000000000000000000000"}));
        System.out.println("T1 = " + new LongestComonStringInArrayOfStrings().largestNumber(new int[]{1,2,8,3,45,59,9,89}) );
    }

    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for(int i=0; i<nums.length; i++){
            arr[i]=String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));

        StringBuilder sb = new StringBuilder();
        for(String s: arr){
            sb.append(s);
        }

        while(sb.charAt(0)=='0'&&sb.length()>1)
            sb.deleteCharAt(0);

        return sb.toString();
    }
}