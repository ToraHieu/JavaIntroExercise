package chapter_11;

import java.util.ArrayList;
import java.util.Date;
import chapter_10.Loan;

public class Ex_06 {
    public static void main(String[] agrs) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Loan());
        list.add(new Date());
        list.add("A string");
        list.add(new CircleFromSimpleGeometricObject());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
