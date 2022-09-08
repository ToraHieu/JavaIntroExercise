package chapter_11;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_04 {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] agrs) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = input.nextInt();
        while (i != 0) {
            list.add(i);
            i = input.nextInt();
        }
        System.out.println(max(list));
    }
    
    public static Integer max(ArrayList<Integer> list) {
        if (list == null || list.size() == 0) 
            return null;
        int n = list.size();
        Integer max = list.get(0);
        for (int i = 1; i < n; i++) {
            if (max.compareTo(list.get(i)) < 0) 
                max = list.get(i);
        }
        
        return max;
    }
}
