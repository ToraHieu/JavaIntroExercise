package chapter_25;

public class Ex_07 {
    public static void main(String[] args) {
        Integer[] numbers = {50, 25, 80, 35, 1, 23, 15, 24, 47, 67, 65, 90, 88, 96};
//        Integer[] numbers = {1, 2, 3, 4, 5, -1, -2, 0};
        Ex07BST<Integer> intTree = new Ex07BST<>(numbers);
        System.out.print("Number of none leaves: " + intTree.getNumberOfNoneLeaves());
    }
}

class Ex07BST<E extends Comparable<E>> extends Ex06BST<E> {
    public Ex07BST(E[] objects) {
        super(objects);
    }

    /** Return the number of leaf nodes */
    public int getNumberOfNoneLeaves() {
        return size - getNumberOfLeaves();
    }
}
