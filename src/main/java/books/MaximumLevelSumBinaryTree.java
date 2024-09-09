package books;

import java.util.HashMap;
import java.util.Map;

class MaximumLevelSumBinaryTree {
    final Map<Integer, Integer> leveSumsMap = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(989,
                null, new TreeNode(10250, new TreeNode(98693, null, null), new TreeNode(-89388, null, new TreeNode(-32127, null, null))));

        System.out.println("new MaximumLevelSumBinaryTree().maxLevelSum(root) = " + new MaximumLevelSumBinaryTree().maxLevelSum(root));
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) return;
        leveSumsMap.merge(level, root.val, Integer::sum);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    public int maxLevelSum(TreeNode root) {
        leveSumsMap.put(1, root.val);
        dfs(root, 1);
        return leveSumsMap.entrySet().stream()
                .sorted((o1, o2) -> {
                    if ((o2.getValue() - o1.getValue()) == 0) {
                        return o1.getKey() - o2.getKey();
                    }
                    return o2.getValue() - o1.getValue();
                }).map(Map.Entry::getKey).limit(1).findFirst().get();
    }

    static class TreeNode {
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
}

