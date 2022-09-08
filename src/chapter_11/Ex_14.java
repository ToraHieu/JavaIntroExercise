package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_14 {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] agrs) {
        ArrayList<Integer> list1 = new ArrayList<>();
        System.out.print("Enter five integers for list1: ");
        for (int i = 0; i < 5; i++) {
            int num = input.nextInt();
            list1.add(num);
        }
        
        ArrayList<Integer> list2 = new ArrayList<>();
        System.out.print("Enter five integers for list2: ");
        for (int i = 0; i < 5; i++) {
            int num = input.nextInt();
            list2.add(num);
        }
        
        ArrayList<Integer> union = union(list1, list2);
        System.out.print("The combined list is " + union.toString());
    }
    
    public static ArrayList<Integer> union (ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < list1.size(); i++) {
            result.add(list1.get(i));
        }
        
        for (int i = 0; i < list2.size(); i++) {
            result.add(list2.get(i));
        }
        
        return result;
    }

}
