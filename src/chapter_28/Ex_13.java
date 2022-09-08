package chapter_28;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex_13 {
    public static void main(String[] args) {
        // Prompt the user to enter nine coins H and T's
        System.out.print("Enter the initial sixteen coins Hs and Ts: ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] initialNode = s.toCharArray();

        SixteenTailModel model = new SixteenTailModel();
        java.util.List<Integer> path =
                model.getShortestPath(SixteenTailModel.getIndex(initialNode));

        if (path != null) {
            System.out.println("The steps to flip the coins are ");
            for (Integer integer : path)
                SixteenTailModel.printNode(
                        SixteenTailModel.getNode(integer));
        } else {
            System.out.print("There's no solution for this pattern.");
        }
    }
}

