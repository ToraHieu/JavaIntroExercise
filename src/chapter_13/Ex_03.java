package chapter_13;

import java.util.ArrayList;

public class Ex_03 {
    public static void main(String[] args) {
        ArrayList<Number> list = new ArrayList<>();
        list.add(5);
        list.add(1.0);
        list.add(6.9);
        list.add(2.4);
        list.add(9);
        list.add(5.0);
        sort(list);
        System.out.print(list.toString());
    }
    
    public static void sort(ArrayList<Number> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).doubleValue() > list.get(j).doubleValue()) {
                    Number temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}
