package books;

public class LLQ {
    /*
    Remove Dups: Write code to remove duplicates from an unsorted linked list.
     */
    public static Node removeDuplicates(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node current = head;
        while (current.next != null) {
            Node next = current;
            Node previous = current;
            while (next != null) {
                if (current.data.equals(next.data)) {
                    previous.next = next.next;
                }
                previous = next;
                next = next.next;
            }
            current = current.next;
        }
        return head;
    }

    public static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("\0\n");
    }

    public static void kthToLast(Node head, int k, int i) {
        if (head == null) {
            return;
        }
        if (i == k) {
            System.out.println("kthToLast(head," + k + "): " + head.data);
        }
        kthToLast(head.next, k, ++i);
    }

    public static void deleteMiddleNode(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node slow = head;
        Node fast = head;
        Node prev = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
    }

    public static int sumList(Node lst1, Node lst2) {
        if (lst1 == null || lst2 == null) {
            return 0;
        }
        int sum = (int) lst2.data + (int) lst1.data + sumList(lst1.next, lst2.next);
        lst1.data = sum % 10;
        return sum / 10;
    }

    public static Node detectLoop(Node head) {
        Node temp = new Node("temp");
        while (head != null) {
            if (head.next == null) {
                return null;
            }
            if (head.next == temp) {
                break;
            }
            Node nex = head.next;
            head.next = temp;
            head = nex;
        }
        return head;
    }

    public static void detectAndRemoveLoop(Node head) {
        if (head == null || head.next == null)
            return;
        Node curr = head;
        while (curr.next != null) {
            Node slow = curr.next;
            Node sPrev = slow;
            Node fast = slow.next;
            Node fPrev = fast;
            while (fast != null && fast.next != null) {
                sPrev = slow;
                slow = slow.next;
                fPrev = fast.next;
                fast = fast.next.next;
                if (fast == curr || slow == curr || fast == slow) {
                    break;
                }
            }
            if (fast != null && fast == curr) {
                fPrev.next = null;
                break;
            }
            if (slow != null && slow == curr) {
                sPrev.next = null;
                break;
            }
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        Node<String> head = new Node<>("A");
        head.next = new Node<>("B");
        head.next.next =new Node<>("C");
        head.next.next.next = new Node<>("D");
        head.next.next.next.next = new Node<>("E");
        head.next.next.next.next.next = new Node<>("F");
        Node<String> g = new Node<>("G");
        head.next.next.next.next.next.next = g;
        head.next.next.next.next.next.next.next = new Node<>("H");
        head.next.next.next.next.next.next.next.next = g;
//        display(head);
//        System.out.printf("Detected loop: %s\n", detectLoop(head).data);
        detectAndRemoveLoop(head);
        System.out.println("After removing loop: ");
        display(head);
//        kthToLast(head, 3, 0);
//        System.out.println("Before Remove Duplicates : ");
//        removeDuplicates(head);
//        System.out.println("After Remove Duplicates : ");
//        display(head);
//
//        System.out.println("Delete Middle Node:");
//        deleteMiddleNode(head);
//        display(head);


        Node<Integer> numList1 = new Node<>(1, new Node<>(2, new Node<>(3, new Node<>(4, new Node<>(5, new Node<>(6, new Node<>(7, null)))))));
        display(numList1);
        Node<Integer> numList2 = new Node<>(9, new Node<>(9, new Node<>(9, new Node<>(9, new Node<>(9, new Node<>(9, new Node<>(9, null)))))));
        display(numList2);
        System.out.println("Sum of the list:");
        int overflow = sumList(numList1, numList2);
        if (overflow > 0) {
            Node overflowNode = new Node(overflow, numList1);
            numList1 = overflowNode;
        }
        display(numList1);
    }
}

class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }


    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}