package chapter_11;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex_12 {
    public static void main(String[] agrs) {
        Double[] array = {3.5, 5.5, 1.0, 2.3, 5.7, 1.2, 0.8, 2.0, 3.0};
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(array));
        double sum = sum(list);
        System.out.println(sum);
    }
    
    public static double sum(ArrayList<Double> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum+= list.get(i);
        }
        
        return sum;
    }

}
