package chapter_25;

import java.util.Stack;

public class Ex_04 {
    public static void main(String[] args) {
        Integer[] numbers = {54, 24, 12, 25, 55, 90, 67, 100, 13, 89, 11, 27, 95, 33, 91, 14, 69, 10, 59, 65, 57};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex04BST<Integer> intTree = new Ex04BST<>(numbers);
        System.out.print("Preorder: ");
        intTree.preorder();
        intTree.inorder();
    }
}

class Ex04BST<E extends Comparable<E>> extends BST<E> {
    public Ex04BST(E[] objects) {
        super(objects);
    }

    @Override
    public void preorder() {
        if (root == null) return;

        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> current = root;
        while (true) {
            // First print the root node and then add left node
            while (current != null) {
                System.out.print(current.element + " ");
                stack.push(current);
                current = current.left;
            }
            // Check if Stack is empty, if yes, exit from everywhere
            if (stack.isEmpty()) {
                return;
            }
            // Pop the element from the stack and go right to the tree
            current = stack.pop();
            current = current.right;
        }
    }
}