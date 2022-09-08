package chapter_25;

import java.util.Iterator;

public class Ex_10 {
    public static void main(String[] args) {
        Integer[] numbers = {50, 25, 80, 35, 1, 23, 15, 24, 47, 67, 65, 90, 88, 96};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex10BST<Integer> intTree = new Ex10BST<>(numbers);
        System.out.print("Preorder method: ");
        intTree.preorder();
        System.out.print("\nPreorder by iterator: ");
        Iterator<Integer> preorderIterator = intTree.preorderIterator();
        while (preorderIterator.hasNext()) {
            System.out.print(preorderIterator.next() + " ");
        }
    }
}

class Ex10BST<E extends Comparable<E>> extends BST<E> {
    public Ex10BST(E[] objects) {
        super(objects);
    }

    /** Return an iterator for traversing the elements in preorder */
    public Iterator<E> preorderIterator() {
        return new PreorderIterator();
    }

    // Inner class InorderIterator
    private class PreorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public PreorderIterator() {
            preorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void preorder() {
            preorder(root);
        }

        /** Inorder traversal from a subtree */
        private void preorder(TreeNode<E> root) {
            if (root == null)return;
            list.add(root.element);
            preorder(root.left);
            preorder(root.right);
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
            preorder(); // Rebuild the list
        }
    }
}
