package chapter_42;

import chapter_25.Tree;

public class Ex_01_02_03 {
    public static void main(String[] args) {
        // Create a 2-4 tree
        ExTree24<Integer> tree = new ExTree24<>();
        tree.insert(34);
        tree.insert(3);
        tree.insert(50);
        tree.insert(20);
        tree.insert(15);
        tree.insert(16);
        tree.insert(25);
        tree.insert(27);
        tree.insert(29);
        tree.insert(24);

        System.out.print("After inserting 24: ");
        printTree(tree);

        tree.insert(23);
        tree.insert(22);
        tree.insert(60);
        tree.insert(70);
        System.out.print("After inserting 70: ");
        printTree(tree);

        tree.delete(34);
        System.out.print("After deleting 34: ");
        printTree(tree);

        tree.delete(25);
        System.out.print("After deleting 25: ");
        printTree(tree);

        tree.delete(50);
        System.out.print("After deleting 50: ");
        printTree(tree);

        tree.delete(16);
        System.out.print("After deleting 16: ");
        printTree(tree);

        tree.delete(3);
        System.out.print("After deleting 3: ");
        printTree(tree);

        tree.delete(15);
        System.out.print("After deleting 15: ");
        printTree(tree);
    }

    public static <E extends Comparable<E>> void printTree(Tree<E> tree) {
        System.out.print("\nPost-order: ");
        tree.postorder();
        System.out.println("\nThe number of nodes is  " + tree.getSize());
    }
}

class ExTree24<E extends Comparable<E>> extends Tree24<E> {
    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(Tree24Node<E> node) {
        if (node == null) return;
        if (node.child.isEmpty()) {
            for (E e: node.elements) {
                System.out.print(e + " ");
            }
            return;
        }

        int i = 0;
        for (Tree24Node<E> child: node.child) {
            inorder(child);
            if (i < node.elements.size())
                System.out.print(node.elements.get(i++) + " ");
        }
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(Tree24Node<E> node) {
        if (node == null) return;
        for (Tree24Node<E> n: node.child) {
            postorder(n);
        }
        for (E e: node.elements) {
            System.out.print(e + " ");
        }
    }
}
