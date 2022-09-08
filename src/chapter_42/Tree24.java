package chapter_42;

import chapter_25.Tree;

import java.util.ArrayList;
import java.util.Iterator;

public class Tree24<E extends Comparable<E>> implements Tree<E> {
    protected Tree24Node<E> root;
    protected int size = 0;

    public Tree24() {
    }

    public Tree24(E[] elements) {
        for (E e : elements) insert(e);
    }

    /** Search an element in the tree */
    @Override
    public boolean search(E e) {
        Tree24Node<E> current = root;

        while (current != null) {
            if (matched(e, current)) {
                return true;
            } else {
                current = getChildNode(e, current);
            }
        }

        return false;
    }

    /** Return true if the element is found in this node */
    private boolean matched(E e, Tree24Node<E> node) {
        for (E element: node.elements)
            if (e.equals(element))
                return true;

        return false;
    }

    /** Locate a child node to search element e */
    private Tree24Node<E> getChildNode(E e, Tree24Node<E> node) {
        if (node.child.isEmpty())
            return null;

        int i = locate(e, node);
        return node.child.get(i);
    }

    /** Locate the insertion point of the element in the node */
    private int locate(E o, Tree24Node<E> node) {
        for (int i = 0; i < node.elements.size(); i++) {
            if (o.compareTo(node.elements.get(i)) <= 0) {
                return i;
            }
        }

        return node.elements.size();
    }

    /** Insert element to a 2- or 3- and return the insertion point */
    private void insert23(E e, Tree24Node<E> rightChildOfE, Tree24Node<E> node) {
        int i = locate(e, node);
        node.elements.add(i, e);
        if (rightChildOfE != null)
            node.child.add(i + 1, rightChildOfE);
    }

    /** Split a 4-node u into u and v and insert e to u or v */
    private E split(E e, Tree24Node<E> rightChildOfE, Tree24Node<E> u, Tree24Node<E> v) {
        v.elements.add(u.elements.remove(2));
        E median = u.elements.remove(1);

        //  Split children for a non-leaf node
        // Move the last two children in node u to node v
        if (u.child.size() > 0) {
            v.child.add(u.child.remove(2));
            v.child.add(u.child.remove(2));
        }

        // Insert e into a 2- or 3- node u or v
        if (e.compareTo(median) < 0)
            insert23(e, rightChildOfE, u);
        else
            insert23(e, rightChildOfE, v);

        return median;
    }

    @Override
    public boolean insert(E e) {
        if (root == null)
            root = new Tree24Node<>(e);
        else {
            // Locate the leaf node for inserting e
            Tree24Node<E> leafNode = null;
            Tree24Node<E> current = root;
            while (current != null) {
                if (matched(e, current)) {
                    return false; // Duplicate element found, nothing inserted
                }
                leafNode = current;
                current = getChildNode(e, current);
            }
            insert(e, null, leafNode);
        }
        size++;
        return true;
    }

    /** Insert element e into node u */
    private void insert(E e, Tree24Node<E> rightChildOfE, Tree24Node<E> u) {
        // Get the search path leads to element e
        ArrayList<Tree24Node<E>> path = path(e);

        for (int i = path.size() - 1; i >= 0; i--) {
            if (u.elements.size() < 3) {
                insert23(e, rightChildOfE, u);
                break;
            } else {
                Tree24Node<E> v = new Tree24Node<>();
                E median = split(e, rightChildOfE, u, v);

                if (u == root) {
                    root = new Tree24Node<>(median);
                    root.child.add(u);
                    root.child.add(v);
                    break; // No further insertion to u's parent needed
                } else {
                    // Use new values for the next iteration in the for loop
                    e = median;
                    rightChildOfE = v;
                    u = path.get(i - 1);
                }
            }
        }
    }

    @Override
    public boolean delete(E e) {
        // Locate the node that contains the element e
        Tree24Node<E> node = root;
        while (node != null) {
            if (matched(e, node)) {
                delete(e, node);
                size--;
                return true; // Element deleted successfully
            } else {
                node = getChildNode(e, node);
            }
        }
        return false;
    }

    /** Delete the specified element from the node */
    private void delete(E e, Tree24Node<E> node) {
        if (node.child.size() == 0) { // e is in a leaf node
            // Get the path that leads to e from the root
            ArrayList<Tree24Node<E>> path = path(e);
            node.elements.remove(e);

            if (node == root) { // Special case
                if (node.elements.size() == 0)
                    root = null; // Empty tree
                return;
            }

            validate(e, node, path); // Check underflow node
        } else { // e is in an internal node
            // Locate the rightmost node in the left subtree of the node
            int index = locate(e, node);
            Tree24Node<E> current = node.child.get(index);
            while (current.child.size() > 0) {
                current = current.child.get(current.child.size() -1 );
            }

            E rightmostElement = current.elements.get(current.elements.size() - 1);

            // Get the path that leads to e from the root
            ArrayList<Tree24Node<E>> path = path(rightmostElement);

            // Replace the deleted element with the rightmost element
            node.elements.set(index, current.elements.remove(current.elements.size() - 1));

            validate(rightmostElement, current, path); // Check underflow
        }
    }

    /** Perform transfer and fusion operation if necessary */
    private void validate(E e, Tree24Node<E> u, ArrayList<Tree24Node<E>> path) {
        for (int i = path.size() - 1; u.elements.size() == 0; i--) {
            Tree24Node<E> parentOfU = path.get(i - 1);
            int k = locate(e, parentOfU); // Index of e in the parent node

            // Check two siblings
            if (k > 0 && parentOfU.child.get(k - 1).elements.size() > 1) {
                leftSiblingTransfer(k, u, parentOfU);
            } else if (k + 1 < parentOfU.child.size() && parentOfU.child.get(k + 1).elements.size() > 1) {
                rightSiblingTransfer(k, u, parentOfU);
            } else if (k - 1 == 0) { // Fusion with a left sibling
                //noinspection ConstantConditions
                Tree24Node<E> leftNode = parentOfU.child.get(k - 1); // Get left sibling of node u

                // Perform a fusion with left sibling on node u
                leftSiblingFusion(k, leftNode, u, parentOfU);

                // Done when root becomes empty
                if (parentOfU == root && parentOfU.elements.size() == 0) {
                    root = leftNode;
                    break;
                }

                u = parentOfU; // Back to the loop to check the parent node
            } else { // Fusion with right sibling (right sibling must exist)
                Tree24Node<E> rightNode = parentOfU.child.get(k + 1); // Get right sibling of node u

                // Perform a fusion with right sibling on node u
                rightSiblingFusion(k, rightNode, u, parentOfU);

                // Done when root becomes empty
                if (parentOfU == root && parentOfU.elements.size() == 0) {
                    root = rightNode;
                    break;
                }

                u = parentOfU; // Back to the loop to check the parent node
            }
        }
    }

    /** Perform a transfer with a left sibling */
    private void leftSiblingTransfer(int k, Tree24Node<E> u, Tree24Node<E> parentOfU) {
        // Move an element from the parent to u
        u.elements.add(0, parentOfU.elements.get(k - 1));

        // Move an element from the left node to the parent
        Tree24Node<E> leftNode = parentOfU.child.get(k - 1);
        parentOfU.elements.set(k - 1, leftNode.elements.remove(leftNode.elements.size() - 1));

        // Move the child link from the left sibling to the node
        if (leftNode.child.size() > 0)
            u.child.add(0, leftNode.child.remove(leftNode.child.size() - 1));
    }

    /** Perform a transfer with right sibling */
    private void rightSiblingTransfer(int k, Tree24Node<E> u, Tree24Node<E> parentOfU) {
        // Move an element from the parent to u
        u.elements.add(0, parentOfU.elements.get(k));

        // Move an element from the right node to the parent
        Tree24Node<E> rightNode = parentOfU.child.get(k + 1);
        parentOfU.elements.set(k, rightNode.elements.remove(0));

        // Move the child link from the right sibling to the node
        if (rightNode.child.size() > 0) {
            u.child.add(rightNode.child.remove(0));
        }
    }

    /** Perform a fusion with a left sibling */
    private void leftSiblingFusion(int k, Tree24Node<E> leftNode, Tree24Node<E> u, Tree24Node<E> parentOfU) {
        // Transfer an element from the parent to the left sibling
        leftNode.elements.add(parentOfU.elements.remove(k -1));

        // Remove the link to the empty node
        parentOfU.child.remove(k);

        // Adjust child links for non-leaf node
        if (u.child.size() > 0)
            leftNode.child.add(u.child.remove(0));
    }
    
    /** Perform a fusion with a right sibling */
    private void rightSiblingFusion(int k, Tree24Node<E> rightNode, Tree24Node<E> u, Tree24Node<E> parentOfU) {
        // Transfer an element from the parent to the right sibling
        rightNode.elements.add(0, parentOfU.elements.remove(k));

        // Remove the link to the empty node
        parentOfU.child.remove(k);

        // Adjust child links for non-leaf node
        if (u.child.size() > 0)
            rightNode.child.add(0, u.child.remove(0));
    }
    
    /** Return a search path that leads to element e */
    private ArrayList<Tree24Node<E>> path(E e) {
        ArrayList<Tree24Node<E>> list = new ArrayList<>();
        Tree24Node<E> current = root; // Start from the root

        while (current != null) {
            list.add(current);
            if (matched(e, current)) {
                break; // Element found
            }
            current = getChildNode(e, current);
        }

        return list;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void inorder() {

    }

    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(Tree24Node<E> node) {
        if (node == null) return;
        for (E e: node.elements) {
            System.out.print(e + " ");
        }

        for (Tree24Node<E> n: node.child) {
            preorder(n);
        }

    }


    @Override
    public void postorder() {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    protected static class Tree24Node<E extends Comparable<E>> {
        ArrayList<E> elements = new ArrayList<>(3);
        ArrayList<Tree24Node<E>> child = new ArrayList<>(4);

        Tree24Node() {}

        Tree24Node(E o) {
            elements.add(o);
        }
    }
}
