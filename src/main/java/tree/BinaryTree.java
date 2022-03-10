package tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class BinaryTree {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        /* Constructed binary tree is
                1
              /   \
             2     3
            /  \   /
           4    5 8
        */
        Node root = new Node<Integer>(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(8));
        HashMap<Integer, LinkedList> nodesBaseOnDepthOrder = new HashMap<Integer, LinkedList>(10);
        bt.listNodesBasedOnDepth(nodesBaseOnDepthOrder, root, 0);
        printTree(nodesBaseOnDepthOrder);

    }

    private static void printTree(HashMap<Integer, LinkedList> nodesBaseOnDepthOrder) {
        for (Integer i : nodesBaseOnDepthOrder.keySet()) {
            LinkedList<Node<Integer>> linkedList = nodesBaseOnDepthOrder.get(i);

            Iterator Iterator = linkedList.iterator();
            while (Iterator.hasNext()) {
                Node<Integer> next = (Node<Integer>) Iterator.next();
                System.out.print(next.getData() + ",");
            }
        }
    }

    public void listNodesBasedOnDepth(Map<Integer, LinkedList> nodes, Node root, int depth) {
        if (root == null)
            return;
        LinkedList linkedList = nodes.get(depth);
        if (linkedList == null) {
            linkedList = new LinkedList();
        }
        linkedList.add(root);
        nodes.put(depth, linkedList);
        depth += 1;
        listNodesBasedOnDepth(nodes, root.getLeft(), depth);
        listNodesBasedOnDepth(nodes, root.getRight(), depth);

    }
}

class Node<T> {
    private Node left;
    private Node right;
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node(Node left, Node right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
