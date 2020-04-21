

public class TrainComposition {
    public class DoublyLinkedListImpl<E> {

        private Node head;
        private Node tail;
        private int size;

        public DoublyLinkedListImpl() {
            size = 0;
        }

        /**
         * this class keeps track of each element information
         *
         * @author java2novice
         */
        private class Node {
            E element;
            Node next;
            Node prev;

            public Node(E element, Node next, Node prev) {
                this.element = element;
                this.next = next;
                this.prev = prev;
            }
        }

        /**
         * returns the size of the linked list
         *
         * @return
         */
        public int size() {
            return size;
        }

        /**
         * return whether the list is empty or not
         *
         * @return
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * adds element at the starting of the linked list
         *
         * @param element
         */
        public void addFirst(E element) {
            Node tmp = new Node(element, head, null);
            if (head != null) {
                head.prev = tmp;
            }
            head = tmp;
            if (tail == null) {
                tail = tmp;
            }
            size++;
            System.out.println("adding: " + element);
        }

        /**
         * adds element at the end of the linked list
         *
         * @param element
         */
        public void addLast(E element) {

            Node tmp = new Node(element, null, tail);
            if (tail != null) {
                tail.next = tmp;
            }
            tail = tmp;
            if (head == null) {
                head = tmp;
            }
            size++;
            System.out.println("adding: " + element);
        }

        /**
         * this method walks forward through the linked list
         */
        public void iterateForward() {

            System.out.println("iterating forward..");
            Node tmp = head;
            while (tmp != null) {
                System.out.println(tmp.element);
                tmp = tmp.next;
            }
        }

        /**
         * this method walks backward through the linked list
         */
        public void iterateBackward() {

            System.out.println("iterating backword..");
            Node tmp = tail;
            while (tmp != null) {
                System.out.println(tmp.element);
                tmp = tmp.prev;
            }
        }

        /**
         * this method removes element from the start of the linked list
         *
         * @return
         */
        public E removeFirst() {
            if (size == 0) throw new java.util.NoSuchElementException();
            Node tmp = head;
            if (size > 1) {
                head = head.next;
                head.prev = null;
            } else {
                head = tail = null;
            }
            size--;
            System.out.println("deleted: " + tmp.element);
            return tmp.element;
        }

        /**
         * this method removes element from the end of the linked list
         *
         * @return
         */
        public E removeLast() {
            if (size == 0) throw new java.util.NoSuchElementException();
            Node tmp = tail;
            if (size > 1) {
                tail = tail.prev;
                tail.next = null;
            } else {
                head = tail = null;
            }
            size--;
            System.out.println("deleted: " + tmp.element);
            return tmp.element;
        }
    }

    DoublyLinkedListImpl<Integer> train = new DoublyLinkedListImpl<Integer>();

    public void attachWagonFromLeft(int wagonId) {
        train.addFirst(wagonId);
    }

    public void attachWagonFromRight(int wagonId) {
        train.addLast(wagonId);
    }

    public int detachWagonFromLeft() {
        return train.removeFirst();
    }

    public int detachWagonFromRight() {
        return train.removeLast();
    }

    int longestSequenceOf1s(int input) {
        char[] chars = Integer.toBinaryString(input).toCharArray();
        int maxSeq = 1;
        int currentLength = 0;
        int previousLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                currentLength++;
            } else {
                previousLength = (i + 1 < chars.length && chars[i + 1] == '1') ? currentLength : 0;
                currentLength = 0;
            }
            maxSeq = Math.max(previousLength + currentLength + 1, maxSeq);
        }
        return maxSeq;
    }

    int flipBit(int a) {
        if (~a == 0) return Integer.BYTES * 8;

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1;
        while (a != 0) {
            if ((a & 1) == 1) {
                currentLength++;
            } else if ((a & 1) == 0) {
                previousLength = (a & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            a >>>= 1;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        TrainComposition tree = new TrainComposition();
//        tree.attachWagonFromLeft(7);
//        tree.attachWagonFromLeft(13);
//        System.out.println(tree.detachWagonFromRight()); // 7
//        System.out.println(tree.detachWagonFromLeft()); // 13


      /*  long durationLS = 0;
        long durationFB = 0;
        for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
            long startTime = System.nanoTime();
            tree.flipBit(i);
            durationFB += System.nanoTime() - startTime;

            startTime = System.nanoTime();
            tree.longestSequenceOf1s(i);
            durationLS += System.nanoTime() - startTime;

            if (Math.abs(durationFB - durationLS) / 1000000 > 1000) {
                System.err.println("ERROR="+i);
                break;
            }
        }*/
//        System.out.println("durationFB = " + durationFB/1000000);
//        System.out.println("durationLS = " + durationLS/1000000);
        System.out.println("tree.longestSequenceOf1s(?) = " + tree.longestSequenceOf1s(63));
        System.out.println("tree.flipBit(?) = " + tree.flipBit(63));
    }
}