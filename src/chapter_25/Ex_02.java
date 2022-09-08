package chapter_25;

public class Ex_02 {
    public static void main(String[] args) {
        Integer[] numbers = {4};
        Ex02BST<Integer> intTree = new Ex02BST<>(numbers);
        System.out.println("Tree height: " + intTree.height());
        System.out.print("Is perfect tree: " + intTree.isPerfect());
    }
}

class Ex02BST<E extends Comparable<E>> extends Ex01BST<E> {
    public Ex02BST(E[] objects) {
        super(objects);
    }

    public boolean isPerfect() {
        return size == Math.pow(2, height() + 1) -1;
    }
}
