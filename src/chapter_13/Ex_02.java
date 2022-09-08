package chapter_13;

import java.util.ArrayList;

public class Ex_02 {
    public static void main(String[] args) {
        ArrayList<Number>  list = new ArrayList<Number>();
        list.add(1);
        list.add(2.0);
        list.add(3.5);
        list.add(4.2);
        list.add(5);
        list.add(6.9);
        shuffle(list);
        System.out.print(list.toString());
    }
    
    public static void shuffle(ArrayList<Number> list) {
        for (int i = 0; i < list.size(); i++) {
            int random = (int) (Math.random() * list.size());
            Number temp = list.get(i);
            list.set(i, list.get(random));
            list.set(random, temp);
        }
    }
}
