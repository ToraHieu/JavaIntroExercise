package chapter_11;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex_07 {
    public static void main(String[] agrs) {
        Integer[] i = {100, 200, 300, 400, 500, 600, 700};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(i));
        shuffle(list);
        System.out.println(list.toString());
    }
    
    public static void shuffle(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            int random = (int) (Math.random() * list.size());
            int swap = list.get(random);
            list.set(random, current);
            list.set(i, swap);
        }
    }

}
