package chapter_25;

import java.util.Iterator;

public class Ex_11 {
    public static void main(String[] args) {
        Integer[] numbers = {50, 25, 80, 35, 1, 23, 15, 24, 47, 67, 65, 90, 88, 96};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex11BST<Integer> intTree = new Ex11BST<>(numbers);
        System.out.print("Postorder method: ");
        intTree.postorder();
        System.out.print("\nPostorder by iterator: ");
        Iterator<Integer> postorderIterator = intTree.postorderIterator();
        while (postorderIterator.hasNext()) {
            System.out.print(postorderIterator.next() + " ");
        }
    }
}

class Ex11BST<E extends Comparable<E>> extends BST<E> {
    public Ex11BST(E[] objects) {
        super(objects);
    }

    /** Return an iterator for traversing the elements in preorder */
    public Iterator<E> postorderIterator() {
        return new PostorderIterator();
    }

    // Inner class InorderIterator
    private class PostorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public PostorderIterator() {
            postorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void postorder() {
            postorder(root);
        }

        /** Inorder traversal from a subtree */
        private void postorder(TreeNode<E> root) {
            if (root == null) return;
            postorder(root.left);
            postorder(root.right);
            list.add(root.element);
        }

        /** More elements for traversing? */
        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        /** Get the current element and move to the next */
        @Override
        public E next() {
            return list.get(current++);
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear(); // Clear the list
            postorder(); // Rebuild the list
        }
    }
}
