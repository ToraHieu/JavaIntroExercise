package chapter_30;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Ex_02 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter the integers between 1 and 100: ");
            int i;
            ArrayList<Integer> list = new ArrayList<>();
            while ((i = in.nextInt()) != 0) {
                list.add(i);
            }
            list.stream().parallel()
                    .filter(e -> e >= 1 && e <= 100)
                    .collect(Collectors.groupingBy(e -> e, TreeMap::new, Collectors.counting()))
                    .forEach((k, v) ->
                        System.out.println(k + " occurs " + v + " " + (v == 1 ? "time" : "times"))
                    );
        }
    }
}
