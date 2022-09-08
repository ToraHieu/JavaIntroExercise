package chapter_11;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex_11 {
    public static void main(String[] agrs) {
        Integer[] array = {3, 5, 8, 99, 5, 6, 1, 2, 9};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        sort(list);
        System.out.println(list.toString());
    }
    
    public static void sort(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
 
}
