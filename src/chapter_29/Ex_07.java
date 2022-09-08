package chapter_29;

import chapter_28.SixteenTailModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Ex_07 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Prompt the user to enter nine coins H and T's
        System.out.print("Enter the initial sixteen coins Hs and Ts: "); // HHTTHTTTTTTHTTHH
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] initialNode = s.toCharArray();

        try (ObjectInputStream objectInput =
                new ObjectInputStream(new FileInputStream("WeightedTailModel16.dat"))) {

            WeightedTailModel16 model = (WeightedTailModel16) objectInput.readObject();
            java.util.List<Integer> path =
                    model.getShortestPath(WeightedTailModel16.getIndex(initialNode));

            if (path != null) {
                System.out.println("The steps to flip the coins are ");
                for (Integer integer : path)
                    WeightedTailModel16.printNode(
                            SixteenTailModel.getNode(integer));
            } else {
                System.out.print("There's no solution for this pattern.");
            }
        }
    }
}
