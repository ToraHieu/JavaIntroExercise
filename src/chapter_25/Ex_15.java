package chapter_25;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Ex_15 {
    public static void main(String[] args) {
        Ex15BST<Integer> tree = new Ex15BST<>(new Integer[]{
                45, 54, 67, 56, 50, 45, 23, 59, 23, 67
        });
        tree.delete(45);
        for (Integer i: tree) {
            if (tree.isLeaf(i))
                System.out.println(tree.getPath(i));
        }
//        Ex15BST<String> tree = new Ex15BST<>();
//        tree.insert("George");
//        tree.insert("Michael");
//        tree.insert("Tom");
//        tree.insert("Adam");
//        tree.insert("Jones");
//        tree.insert("Peter");
//        tree.insert("Daniel");
//        printTree(tree);
//
//        System.out.println("\nAfter delete George:");
//        tree.delete("George");
//        printTree(tree);
//
//        System.out.println("\nAfter delete Adam:");
//        tree.delete("Adam");
//        printTree(tree);
//
//        System.out.println("\nAfter delete Michael:");
//        tree.delete("Michael");
//        printTree(tree);
    }

//    public static <E extends Comparable<E>> void printTree(Ex15BST<E> tree) {
//        // Traverse tree
//        System.out.print("Inorder (sorted): ");
//        tree.inorder();
//        System.out.print("\nPostorder: ");
//        tree.postorder();
//        System.out.print("\nPreorder: ");
//        tree.preorder();
//        System.out.print("\nThe number of nodes is " + tree.getSize());
//        System.out.println();
//    }
}