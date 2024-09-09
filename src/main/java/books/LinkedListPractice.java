package books;

import java.util.Stack;

public class LinkedListPractice {
    public static void main(String[] args) {
        ListElement<String> l = new ListElement<>("L");
        ListElement<String> g = new ListElement<>("G");
        ListElement<String> y = new ListElement<>("Y");
        ListElement<String> f = new ListElement<>("F");
        ListElement<String> mf = new ListElement<>("MF");
        l.setNext(g);
        g.setNext(y);
        y.setNext(f);
        f.setNext(mf);
        mf.setNext(null);
        LinkedListPractice ls = new LinkedListPractice();
        System.out.print("List: ");

//        ListElement reversed = ls.reverse2(l);
//        System.out.print("\nReversed List: ");
//        ls.printRecursive(reversed);
        ListElement listElement = ls.mThToLastElement(l, 0);
        System.out.printf("\nM-th[%s] of List: %s", 0, listElement.value());
        listElement = ls.mThToLastElement(l, 2);
        System.out.printf("\nM-th[%s] of List: %s", 2, listElement.value());

        System.out.println(" ======Reverse List====" );
        ListElement reversed = reverseLinkedList(new ListElement("a", new ListElement("b", new ListElement("c", new ListElement<>("d",null)))));
        ls.printRecursive(reversed);


    }

    public static String removeChars(String str, String remove) {
        char[] s = str.toCharArray();
        char[] r = remove.toCharArray();
        int src, dst = 0;
// flags automatically initialized to false, size of 128 assumes ASCII
        boolean[] flags = new boolean[128];
// Set flags for characters to be removed
        for (src = 0; src < r.length; ++src) {
            flags[r[src]] = true;
        }
// Now loop through all the characters, // copying only if they aren’t flagged
        for (src = 0; src < s.length; ++src) {
            if (!flags[s[src]]) {
                s[dst++] = s[src];
            }
        }
        return new String(s, 0, dst);
    }


    public ListElement mThToLastElement(ListElement head, int m) {

        ListElement cursor = m == 0 ? head.next() : head;

        for (int i = 0; i < m; i++) {
            if (cursor.next() != null) {
                cursor = cursor.next();
            } else {
                return null;
            }
        }
        ListElement mBehind = head;
        while (cursor != null) {
            cursor = cursor.next();
            mBehind = mBehind.next();
        }
        return mBehind;
    }

    public ListElement mThToLastElementStillBigO_n(ListElement head, int m) {

        ListElement cursor = head;
        int listSize = 0;
        while (cursor != null) {
            listSize++;
            cursor = cursor.next();
        }
        cursor = head;
        m = m <= 0 ? 1 : m;
        int el = listSize - m;
        while (el > 0) {
            cursor = cursor.next();
            el--;
        }
        return cursor;
    }

    public ListElement mThToLastElementWithExtraSpace(ListElement head, int m) {

        Stack<ListElement> stack = new Stack<>();
        ListElement cursor = head;
        while (cursor != null) {
            stack.push(cursor);
            cursor = cursor.next();
        }
        if (m == 0) {
            return !stack.isEmpty() ? stack.pop() : null;
        }
        if (m > stack.size()) {
            return head;
        }
        while (m > 1) {
            m--;
            stack.pop();
        }
        return stack.pop();
    }

    public static ListElement reverseLinkedList(ListElement head) {
        ListElement newHead = head;
        ListElement cursor = head.next();
        head = head.next();
        newHead.setNext(null);
        while (cursor != null) {
            head = head.next();
            cursor.setNext(newHead);
            newHead = cursor;
            cursor = head;
        }
        return newHead;
    }

    public ListElement reverse(ListElement head) {
        if (head == null) {
            return head;
        }
        if (head.next() == null) {
            return head;
        }
        ListElement newHeadNode = reverse(head.next());

        head.next().setNext(head);
        head.setNext(null);
        return newHeadNode;
    }

    public ListElement reverse2(ListElement head) {
        if (head == null) {
            return null;
        }
        return reverseUtil(null, head);
    }

    public ListElement reverseUtil(ListElement prev, ListElement current) {
        if (current.next() == null) {
            current.setNext(prev);
            return current;
        }
        ListElement next = current.next();
        current.setNext(prev);
        return reverseUtil(current, next);
    }

    public void printLinkedList(ListElement head) {
        ListElement cursor = head;
        while (cursor != null) {
            System.out.printf("%s ", cursor.value());
            cursor = cursor.next();
        }
    }

    public void printRecursive(ListElement head) {
        if (head == null) return;
        System.out.printf("%s ", head.value());
        printRecursive(head.next());
    }
}

class ListElement<T> {
    private ListElement<T> next;
    private T data;

    public ListElement(T value) {
        data = value;
    }

    public ListElement(T data, ListElement<T> next) {
        this.next = next;
        this.data = data;
    }

    public ListElement<T> next() {
        return next;
    }

    public T value() {
        return data;
    }

    public ListElement setNext(ListElement<T> elem) {
        next = elem;
        return next;
    }

    public void setValue(T value) {
        data = value;
    }
}