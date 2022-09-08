package chapter_25;

import java.util.LinkedList;
import java.util.Queue;

public class Ex_01 {
    public static void main(String[] args) {
//        Integer[] numbers = {50, 25, 80, 1, 23, 15, 24, 35, 47, 67, 65, 90, 88};
        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex01BST<Integer> intTree = new Ex01BST<>(numbers);
        System.out.print("Breadth first: ");
        intTree.breadthFirstTraversal();
        System.out.print("\nTree height: " + intTree.height());
    }
}

class Ex01BST<E extends Comparable<E>> extends BST<E> {
    public Ex01BST() {
    }

    public Ex01BST(E[] objects) {
        super(objects);
    }

    public void breadthFirstTraversal() {
        Queue<BST.TreeNode<E>> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
//            breadthFirstTraversal(root, queue);
        }
        while (!queue.isEmpty()) {
            BST.TreeNode<E> current = queue.poll();
            System.out.print(current.element + " ");
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }

    public int height() {
        if (root == null)
            return -1;
        return getHeight(root);
    }

    protected int getHeight(TreeNode<E> current) {
        int leftHeight = 0, rightHeight = 0;
        if (current.left != null) {
            leftHeight = getHeight(current.left) + 1;
        }
        if (current.right != null) {
            rightHeight = getHeight(current.right) + 1;
        }
        return Math.max(leftHeight, rightHeight);
    }
}
