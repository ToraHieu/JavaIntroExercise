package chapter_25;

import java.util.Stack;

public class Ex_05 {
    public static void main(String[] args) {
        Integer[] numbers = {54, 24, 12, 25, 55, 90, 67, 100, 13, 89, 11, 27, 95, 33, 91, 14, 69, 10, 59, 65, 57};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex05BST<Integer> intTree = new Ex05BST<>(numbers);
        System.out.print("Postorder: ");
        intTree.postorder();
    }
}

class Ex05BST<E extends Comparable<E>> extends BST<E> {
    public Ex05BST(E[] objects) {
        super(objects);
    }

    @Override
    public void postorder() {
        if (root == null) return;

        Stack<TreeNode<E>> s1 = new Stack<>();
        Stack<TreeNode<E>> s2 = new Stack<>();
        // Push the root node into first stack.
        s1.push(root);
        while (!s1.isEmpty()) {
            // Take out the root and insert into second stack.
            TreeNode<E> temp = s1.pop();
            s2.push(temp);
            // Now we have the root, push the left and right child of root into the first stack.
            if(temp.left!=null){
                s1.push(temp.left);
            }
            if(temp.right!=null){
                s1.push(temp.right);
            }
        }
        // Once the all node are traversed, take out the nodes from second stack and print it.
        while(!s2.isEmpty()){
            System.out.print(s2.pop().element + " ");
        }
    }
}