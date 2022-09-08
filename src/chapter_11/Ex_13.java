package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_13 {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] agrs) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("Enter ten integers: ");
        for (int i = 0; i < 10; i++) {
            int num = input.nextInt();
            list.add(num);
        }
        removeDuplicate(list);
        System.out.println(list.toString());
    }
    
    public static void removeDuplicate(ArrayList<Integer> list) {
        ArrayList<Integer> temp = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            if (temp.contains(list.get(i))) {
                list.remove(i);
                i--;
            } else {
                temp.add(list.get(i));
            }
        }
    }

}
