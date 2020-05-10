import java.util.*;

public class OneMonthChallenge {
    /**
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * <p>
     * Note:
     * <p>
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * <p>
     * Example 1:
     * <p>
     * Input: [2,2,1]
     * Output: 1
     * Example 2:
     * <p>
     * Input: [4,1,2,1,2]
     * Output: 4
     *
     * @param nums
     * @return
     */

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        Optional<Integer> singleNumber = map.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst();
        return singleNumber.get();

    }

    /**
     * Happy Number
     * Solution
     * Write an algorithm to determine if a number n is "happy".
     * <p>
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     * <p>
     * Return True if n is a happy number, and False if not.
     * <p>
     * Example:
     * <p>
     * Input: 19
     * Output: true
     * Explanation:
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int tempN = n;

        while (true) {
            int firstSum = sumSqrtDigits(n);
            int secondSum = sumSqrtDigits(sumSqrtDigits(tempN));
            if (secondSum == 1 || firstSum == 1) {
                return true;
            }
            if (secondSum == firstSum) {
                return false;
            }
            tempN = secondSum;
            n = firstSum;
        }
    }

    private int sumSqrtDigits(int num) {
        int sum = 0;

        while (num > 0) {
            sum += (int) Math.pow(num % 10, 2);
            num = num / 10;
        }
        return sum;
    }

    /**
     * Maximum Subarray
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * Example:
     * <p>
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Follow up:
     * <p>
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     *
     * @param nums
     * @return
     */

    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        long runSum = Integer.MIN_VALUE;
        long result = Integer.MIN_VALUE;
        for (int num : nums) {
            runSum = Math.max(num, runSum + num);
            result = Math.max(result, runSum);
        }

        return (int) result;
    }

    public int maxSubArray2(int[] nums) {
        int size = nums.length;

        int besRun = Integer.MIN_VALUE;
        int currentRun = 0;

        for (int i = 0; i < size; i++) {
            if (nums[i] > currentRun + nums[i]) {
                currentRun = nums[i];
            } else
                currentRun = currentRun + nums[i];

            if (besRun < currentRun) {
                besRun = currentRun;
            }
        }
        return besRun;
    }

    public int maxSubArray(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        return maxSumSubarray(nums, low, high);
    }

    private int maxSumSubarray(int[] nums, int low, int high) {
        if (high == low) {
            return nums[high];
        }
        int mid = (high + low) / 2;

        int maxSumSubarrayLow = maxSumSubarray(nums, low, mid);
        int maxSumSubarrayHigh = maxSumSubarray(nums, mid + 1, high);
        int crossingSubarray = maxCrossingSubarray(nums, low, mid, high);
        return Math.max(Math.max(maxSumSubarrayLow, maxSumSubarrayHigh), crossingSubarray);
    }

    private int maxCrossingSubarray(int nums[], int low, int mid, int high) {

        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + nums[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for (int i = mid + 1; i <= high; i++) {
            sum = sum + nums[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        return (leftSum + rightSum);
    }

    /**
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * <p>
     * Example:
     * <p>
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Note:
     * <p>
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     *
     * @param nums
     */
    public int[] moveZeroes(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left++;
            }
        }
        return nums;
    }

    /**
     * Best Time to Buy and Sell Stock II
     * Say you have an array prices for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     * <p>
     * Example 1:
     * <p>
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     * Example 2:
     * <p>
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     * engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     * <p>
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * @param prices
     * @return
     */

    public int maxProfit(int[] prices) {

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                profit += diff;
            }
        }
        return profit;
    }

    /**
     * Group Anagrams
     * Given an array of strings, group anagrams together.
     * <p>
     * Example:
     * <p>
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note:
     * <p>
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            List<String> list = map.computeIfAbsent(calculateHashCode(str), k -> new ArrayList<>());
            list.add(str);
            map.put(calculateHashCode(str), list);
        }
        return new ArrayList<>(map.values());
    }

    private String calculateHashCode(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        int[] geneticSequence = new int[26];
        for (Character ch : str.toCharArray()) {
            geneticSequence[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder(53);
        for (int g : geneticSequence) {
            sb.append(g + '~');
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Counting Elements
     * Given an integer array arr, count element x such that x + 1 is also in arr.
     * <p>
     * If there're duplicates in arr, count them seperately.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: arr = [1,2,3]
     * Output: 2
     * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
     * Example 2:
     * <p>
     * Input: arr = [1,1,3,3,5,5,7,7]
     * Output: 0
     * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
     * Example 3:
     * <p>
     * Input: arr = [1,3,2,3,5,0]
     * Output: 3
     * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
     * Example 4:
     * <p>
     * Input: arr = [1,1,2,2]
     * Output: 2
     * Explanation: Two 1s are counted cause 2 is in arr.
     *
     * @param arr
     * @return
     */
    public int countElements(int[] arr) {
        Set<Integer> tempSet = new HashSet<>();
        for (int value : arr) {
            tempSet.add(value);
        }
        int count = 0;
        for (int value : arr) {
            if (tempSet.contains(value + 1)) {
                count++;
            }
        }
        return count;
    }


/**
 *   Middle of the Linked List
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5]
 * Output: Node 3 from this list (Serialization: [3,4,5])
 * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
 * Note that we returned a ListNode object ans, such that:
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
 * Example 2:
 *
 * Input: [1,2,3,4,5,6]
 * Output: Node 4 from this list (Serialization: [4,5,6])
 * Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Note:
 *
 * The number of nodes in the given list will be between 1 and 100.
 */
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            p = p.next.next;
            head = head.next;
        }
        return head;
    }

    /**
     * Backspace String Compare
     * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
     * <p>
     * Note that after backspacing an empty text, the text will continue empty.
     * <p>
     * Example 1:
     * <p>
     * Input: S = "ab#c", T = "ad#c"
     * Output: true
     * Explanation: Both S and T become "ac".
     * Example 2:
     * <p>
     * Input: S = "ab##", T = "c#d#"
     * Output: true
     * Explanation: Both S and T become "".
     * Example 3:
     * <p>
     * Input: S = "a##c", T = "#a#c"
     * Output: true
     * Explanation: Both S and T become "c".
     * Example 4:
     * <p>
     * Input: S = "a#c", T = "b"
     * Output: false
     * Explanation: S becomes "c" while T becomes "b".
     * Note:
     * <p>
     * 1 <= S.length <= 200
     * 1 <= T.length <= 200
     * S and T only contain lowercase letters and '#' characters.
     * Follow up:
     * <p>
     * Can you solve it in O(N) time and O(1) space?
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        return applyBackSpace(S).equalsIgnoreCase(applyBackSpace(T));
    }

    private String applyBackSpace(String str) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * Diameter of Binary Tree
     * Solution
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * <p>
     * Example:
     * Given a binary tree
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     * <p>
     * Note: The length of path between two nodes is represented by the number of edges between them.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);
        int lDiameter = diameterOfBinaryTree(root.left);
        int rDiameter = diameterOfBinaryTree(root.right);
        return Math.max(lDepth + rDepth + 1, Math.max(lDiameter, rDiameter));
    }

    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }

    /**
     * We have a collection of stones, each stone has a positive integer weight.
     * <p>
     * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
     * <p>
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
     * <p>
     * <p>
     * Note:
     * <p>
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     * Hide Hint #1
     * Simulate the process. We can do it with a heap, or by sorting some list of stones every time we take a turn.
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        int sl = stones.length;
        int stoneWeight = 0;
        while (sl > 1) {
            Arrays.sort(stones, 0, sl);

            int lastElemIndex = sl - 1;
            stones[lastElemIndex - 1] = stones[lastElemIndex] - stones[lastElemIndex - 1];
            sl--;
        }

        if (stones.length >= 1) {
            stoneWeight = stones[0];
        }

        return stoneWeight;

    }
}

/**
 * Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 */

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> helperStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.stack = new Stack<>();
        this.helperStack = new Stack<>();
    }

    public int top() {
        return stack.peek();
    }

    public void push(int value) {
        if (!helperStack.isEmpty()) {
            if (value < helperStack.peek()) {
                helperStack.push(value);
            }
        } else {
            helperStack.push(value);
        }
        stack.push(value);
    }

    public void pop() {
        if (stack.empty()) {
            return;
        }
        if (stack.peek().equals(helperStack.peek())) {
            helperStack.pop();
        }
        stack.pop();
    }

    public Integer getMin() {
        if (helperStack.isEmpty()) {
            return null;
        }
        return helperStack.peek();
    }

}