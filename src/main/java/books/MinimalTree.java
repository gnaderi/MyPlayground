package books;

/**
 * Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algo­ rithm to create a binary search tree with minimal height.
 */
public class MinimalTree {
    static TreeNode<Integer> prev;

    public static void main(String[] args) {
        int[] sArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = minimalTree(sArray, 0, sArray.length - 1);
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(4);
        display(root);
        System.out.println("\nisBST = " + validateBST(root));
    }

    public static TreeNode minimalTree(int[] nums, int low, int high) {
        if (nums.length == 0) {
            return null;
        }

        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = minimalTree(nums, low, mid - 1);
        root.right = minimalTree(nums, mid + 1, high);
        return root;
    }

    public static boolean validateBST(TreeNode<Integer> node) {
        if (node == null) {
            return true;
        }
        if (!validateBST(node.left))
            return false;

        if (prev != null && node.data <= prev.data)
            return false;

        prev = node;

        return validateBST(node.right);
    }

    public static void display(TreeNode node) {
        if (node == null) return;
        display(node.left);
        display(node.right);
        System.out.print(node.data + ">");
    }
}

class TreeNode<T> {
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}