package chapter_25;

import java.util.Stack;

public class Ex_06 {
    public static void main(String[] args) {
        Integer[] numbers = {50, 25, 80, 35, 1, 23, 15, 24, 47, 67, 65, 90, 88, 96};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex06BST<Integer> intTree = new Ex06BST<>(numbers);
        intTree.delete(25);
        intTree.delete(35);
        System.out.print("Number of leaves: " + intTree.getNumberOfLeaves());
    }
}

class Ex06BST<E extends Comparable<E>> extends BST<E> {
    public Ex06BST(E[] objects) {
        super(objects);
    }

    /** Return the number of leaf nodes */
    public int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    protected int getNumberOfLeaves(TreeNode<E> root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        return getNumberOfLeaves(root.left) + getNumberOfLeaves(root.right);
    }
}

