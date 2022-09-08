package chapter_26;

import chapter_25.Ex15BST;

public class Ex_04 {
    public static void main(String[] args) {
        Ex04AVLTree<Integer> tree = new Ex04AVLTree<>();
        for (int i = 1; i <= 100; i++) {
            tree.add(i);
        }
        for (Integer i: tree) {
            if (tree.isLeaf(i))
                System.out.println(tree.getPath(i));
        }
    }
}

class Ex04AVLTree<E extends Comparable<E>> extends Ex15BST<E> {
    /** Create an empty AVL tree */
    public Ex04AVLTree() {
    }

    /** Create an AVL tree from an array of objects */
    public Ex04AVLTree(E[] objects) {
        super(objects);
    }

    /** Override createNewNode to create an AVLTreeNode */
    @Override
    protected AVLTreeNode<E> createNewNode(E element) {
        return new AVLTreeNode<>(element);
    }

    /** Override createNewNode to create an AVLTreeNode */
    protected AVLTreeNode<E> createNewNode(E element, TreeNode<E> p) {
        return new AVLTreeNode<>(element, (AVLTreeNode<E>) p);
    }

    /** Insert an element and re-balance if necessary */
    @Override
    public boolean insert(E element) {
        boolean successful = super.insert(element);
        if (!successful)
            return false; // element is already in the tree
        else {
            balancePath(element); // Balance from element to the root if necessary
        }

        return true; // element is inserted
    }

    /** Update the height of a specified node */
    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null) // node is a leaf
            node.height = 0;
        else if (node.left == null) // node has no left subtree
            node.height = 1 + ((AVLTreeNode<E>)(node.right)).height;
        else if (node.right == null) // node has no right subtree
            node.height = 1 + ((AVLTreeNode<E>)(node.left)).height;
        else
            node.height = 1 +
                    Math.max(((AVLTreeNode<E>)(node.right)).height,
                            ((AVLTreeNode<E>)(node.left)).height);
    }

    /** Balance the nodes in the path from the specified
     * node to the root if necessary
     */
    private void balancePath(E element) {
        java.util.ArrayList<TreeNode<E>> path = path(element);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
            updateHeight(A);

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>)A.left) <= 0) {
                        balanceLL(A); // Perform LL rotation
                    }
                    else {
                        balanceLR(A); // Perform LR rotation
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>)A.right) >= 0) {
                        balanceRR(A); // Perform RR rotation
                    }
                    else {
                        balanceRL(A); // Perform RL rotation
                    }
            }
        }
    }

    /** Return the balance factor of the node */
    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null) // node has no right subtree
            return -node.height;
        else if (node.left == null) // node has no left subtree
            return +node.height;
        else
            return ((AVLTreeNode<E>)node.right).height -
                    ((AVLTreeNode<E>)node.left).height;
    }

    /** Balance LL (see Figure 26.3) */
    private void balanceLL(TreeNode<E> A) {
        TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy

        if (A == root) {
            root = B;
        }
        else {
            if (A.parent.left == A) {
                A.parent.left = B;
            }
            else {
                A.parent.right = B;
            }
        }
        B.parent = A.parent; // Update B's parent

        A.left = B.right; // Make T2 the left subtree of A and update its parent
        if (A.left != null) A.left.parent = A;
        B.right = A; // Make A the right child of B and update A's parent
        A.parent = B;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }

    /** Balance LR (see Figure 26.5) */
    private void balanceLR(TreeNode<E> A) {
        TreeNode<E> B = A.left; // A is left-heavy
        TreeNode<E> C = B.right; // B is right-heavy

        if (A == root) {
            root = C;
        }
        else {
            if (A.parent.left == A) {
                A.parent.left = C;
            }
            else {
                A.parent.right = C;
            }
        }
        C.parent = A.parent; // Update C's parent

        A.left = C.right; // Make T3 the left subtree of A
        A.left.parent = A;
        B.right = C.left; // Make T2 the right subtree of B
        B.right.parent = B;
        C.left = B;
        B.parent = C;
        C.right = A;
        A.parent = C;

        // Adjust heights
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }

    /** Balance RR (see Figure 26.4) */
    private void balanceRR(TreeNode<E> A) {
        TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

        if (A == root) {
            root = B;
        }
        else {
            if (A.parent.left == A) {
                A.parent.left = B;
            }
            else {
                A.parent.right = B;
            }
        }
        B.parent = A.parent;

        A.right = B.left; // Make T2 the right subtree of A
        if (A.right != null) A.right.parent = A;
        B.left = A; // Make A the left child of B
        A.parent = B;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }

    /** Balance RL (see Figure 26.6) */
    private void balanceRL(TreeNode<E> A) {
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy

        if (A == root) {
            root = C;
        }
        else {
            if (A.parent.left == A) {
                A.parent.left = C;
            }
            else {
                A.parent.right = C;
            }
        }
        C.parent = A.parent;

        A.right = C.left; // Make T2 the right subtree of A
        if (A.right != null) A.right.parent = A;
        B.left = C.right; // Make T3 the left subtree of B
        if (B.left != null) B.left.parent = B;
        C.left = A;
        A.parent = C;
        C.right = B;
        B.parent = C;

        // Adjust heights
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }

    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    @Override
    public boolean delete(E element) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (element.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (current.parent == null) {
                root = current.right;
                root.parent = null;
            }
            else {
                if (element.compareTo(current.parent.element) < 0) {
                    current.parent.left = current.right;
                }
                else {
                    current.parent.right = current.right;
                }
                // Correct right child's parent if it exists
                if (current.right != null) current.right.parent = current.parent;

                // Balance the tree if necessary
                balancePath(current.parent.element);
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (rightMost.parent.right == rightMost)
                rightMost.parent.right = rightMost.left;
            else
                // Special case: current.parent == current
                rightMost.parent.left = rightMost.left;
            // Correct rightmost's parent if it exists
            if (rightMost.left != null) rightMost.left.parent = rightMost.parent;

            balancePath(rightMost.parent.element);
        }

        size--;
        return true; // Element deleted successfully
    }

    /** AVLTreeNode is TreeNode plus height */
    protected static class AVLTreeNode<E> extends chapter_25.Ex15BST.TreeNode<E> {
        protected int height = 0; // New data field

        public AVLTreeNode(E o) {
            super(o);
        }

        public AVLTreeNode(E o, AVLTreeNode<E> p) {
            super(o, p);
        }
    }
}

