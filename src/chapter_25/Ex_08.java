package chapter_25;

import java.util.ListIterator;

public class Ex_08 {
    public static void main(String[] args) {
        Integer[] numbers = {50, 25, 80, 35, 1, 23, 15, 24, 47, 67, 65, 90, 88, 96};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex08BST<Integer> intTree = new Ex08BST<>(numbers);
        System.out.println("Forward traverse: ");
        ListIterator<Integer> iterator = intTree.bidirectionalIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\nBackward traverse: ");
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
    }
}

class Ex08BST<E extends Comparable<E>> extends BST<E> {
    public Ex08BST() {
    }

    public Ex08BST(E[] objects) {
        super(objects);
    }

    /** Obtain an iterator. Use inorder. */
    public java.util.ListIterator<E> bidirectionalIterator() {
        return new InorderListIterator();
    }

    // Inner class InorderIterator
    private class InorderListIterator implements java.util.ListIterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int cursor = 0; // The cursor position

        public InorderListIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return cursor < list.size();
        }

        @Override
        public E next() {
            return list.get(cursor++);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            return list.get(--cursor);
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override // Remove the element returned by the last next()
        public void remove() {
            throw new UnsupportedOperationException("Removing an element from the iterator is not supported");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Setting an element from the iterator is not supported");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("Adding an element from the iterator is not supported");
        }
    }
}
