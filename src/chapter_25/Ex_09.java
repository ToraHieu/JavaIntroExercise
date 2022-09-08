package chapter_25;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Ex_09 {
    // The test may be a bit confusing but the methods seem to work.
    public static void main(String[] args) {
        Integer[] numbers = {50, 25, 80, 35, 1, 23, 15, 24, 47, 67, 65, 90, 88, 96};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex09BST<Integer> intTree = new Ex09BST<>(numbers);
        BST<Integer> integerBST = new BST<>(numbers);
        System.out.println("intTree: " + intTree.toString());
        System.out.println("integerBST: " + integerBST.toString());

        System.out.println("intTree equals integerBST: " + intTree.equals(integerBST));
        integerBST.remove(96);
        System.out.println("intTree equals integerBST after remove: " + intTree.equals(integerBST));
        intTree.remove(96);
        System.out.println("intTree equals integerBST now?: " + intTree.equals(integerBST));
        try {
            //noinspection unchecked
            Ex09BST<Integer> cloned = (Ex09BST<Integer>) intTree.clone();
            System.out.println("intTree is cloned: " + (intTree == cloned));
            System.out.println("intTree equals cloned: " + intTree.equals(cloned));

            intTree.remove(88);
            System.out.print("Post order: "); intTree.postorder();
            System.out.print("\nPre order: "); intTree.preorder();

            System.out.print("\nPost order: "); cloned.postorder();
            System.out.print("\nPre order: "); cloned.preorder();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Ex09BST<E extends Comparable<E>> extends BST<E> implements Cloneable {
    public Ex09BST() {
    }

    public Ex09BST(E[] objects) {
        super(objects);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BST))
            return false;

        BST<E> bst;
        try {
            //noinspection unchecked
            bst = (BST<E>)obj;
        } catch (Exception e) {
            System.out.print(e.toString());
            return false;
        }
        Iterator<E> iterator = bst.iterator();
        Iterator<E> selfIterator = this.iterator();

        while (iterator.hasNext() && selfIterator.hasNext()) {
            if (iterator.next().compareTo(selfIterator.next()) != 0)
                return false;
        }

        return !iterator.hasNext() && !selfIterator.hasNext();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //noinspection unchecked
        Ex09BST<E> object = (Ex09BST<E>) super.clone();
        object.clear();
        // cloning by creating new nodes from the root
        object.root = cloneTree(this.root);

        // cloning by adding elements in a breadth-first traversal
//        Queue<TreeNode<E>> queue = new LinkedList<>();
//        if (root != null) {
//            queue.offer(root);
//        }
//        while (!queue.isEmpty()) {
//            TreeNode<E> current = queue.poll();
//            object.add(current.element);
//            if (current.left != null) queue.offer(current.left);
//            if (current.right != null) queue.offer(current.right);
//        }

        return object;
    }

    protected TreeNode<E> cloneTree(TreeNode<E> root) {
        if (root == null) return null;
        TreeNode<E> newNode = new TreeNode<>(root.element);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }
}
