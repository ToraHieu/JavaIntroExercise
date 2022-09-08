package chapter_28;

public class Ex_14 {
    public static void main(String[] args) {
        SixteenTailModel sixteenTailModel = new SixteenTailModel();
        System.out.print("The number of starting patterns that don't have a solution: " +
                (SixteenTailModel.NUMBER_OF_NODES - sixteenTailModel.tree.getNumberOfVerticesFound()));
    }
}
