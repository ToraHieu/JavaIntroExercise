package chapter_19;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex_08 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 1 , 2, 3, 4, 5 , 6, 7 ,8 , 9));
        shuffle(list);
        System.out.print(list);
    }

    public static <E> void shuffle(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            int randomIndex = (int) (Math.random() * list.size());
            // Take the Object at "i" index and set it at "randomIndex" index,
            // the original Object at "randomIndex' is then set at "i" index.
            list.set(i, list.set(randomIndex, list.get(i)));
        }
    }
}
