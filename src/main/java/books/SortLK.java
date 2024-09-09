package books;


import java.util.Random;

class SortLK {
    public static void main(String[] args) {
        SortLK sortLK = new SortLK();
        ListNode head = new ListNode(4);
        ListNode next = head;
        Random rand = new Random();
        int i = 0;
        int n = 1000;
        while (++i < n) {
            next.next = new ListNode(rand.nextInt(n));
            next = next.next;
        }
        next.next = null;


        ListNode ListNode = sortLK.sortList(head);
        while (ListNode != null) {
            System.out.print(ListNode.val + " ,");
            ListNode = ListNode.next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = merge(result, lists[i]);
        }
        return result;
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    private ListNode merge1(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        ListNode head = new ListNode(-1);
        ListNode current = head;
        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        while (left != null) {
            current.next = left;
            current = current.next;
            left = left.next;
        }
        while (right != null) {
            current.next = right;
            right = right.next;
            current = current.next;
        }
        current.next = null;
        return head.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(slow);


        return merge1(left, right);
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

 