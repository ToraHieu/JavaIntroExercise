package chapter_25;

import java.util.Stack;

public class Ex_03 {
    public static void main(String[] args) {
        Integer[] numbers = {54, 24, 12, 25, 55, 90, 67, 100, 13, 89, 11, 27, 95, 33, 91, 14, 69, 10, 59, 65};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex03BST<Integer> intTree = new Ex03BST<>(numbers);
        System.out.print("Inorder: ");
        intTree.inorder();

    }
}

class Ex03BST<E extends Comparable<E>> extends BST<E> {
    public Ex03BST(E[] objects) {
        super(objects);
    }

    @Override
    public void inorder() {
        if (root == null) return;

        TreeNode<E> current = root;
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(current);

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.element + " ");

            current = current.right;
        }
    }
}

