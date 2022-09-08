package chapter_20;

import java.util.*;

// A mess here, pray for the souls trying to understand it.
// This is a test class, you're free to delete it anyway.
public class TestCollectionsStaticMethods {
    public static void main(String[] args) {
        Obj1 o1 = new Obj1(0);
        Obj1 o2 = new Obj1(0);
        o1.i = 1;
        o2.i = 2;

        System.out.println(o1 == o2);
        System.out.println(o1.i + " " + o2.i);

        List<Obj1> l1 = new ArrayList<>();
        l1.add(new Obj1(11));
        l1.add(new Obj1(12));
        l1.add(new Obj1(13));

        Collections.fill(l1, o2);

//        l1 = Collections.nCopies(3, o1);
//        System.out.println(l1.get(0) == l1.get(1));
//        System.out.println(o1 == l1.get(1));
//
//        l1.get(0).i = 0;
        System.out.println(l1.get(0).i + " " + l1.get(1).i);
        l1.get(0).i = 1001;
        l1.get(2).i = 3003;
        System.out.println(l1.get(0).i + " " + l1.get(1).i);


        Collection<String> collection1 = Arrays.asList("red", "cyan");
        Collection<String> collection2 = Arrays.asList("red", "blue");
        Collection<String> collection3 = Arrays.asList("pink", "tan");
        System.out.println(Collections.disjoint(collection1, collection2) + collection1.toString() +"\t" + collection2.toString());
        System.out.println(Collections.disjoint(collection1, collection3) + collection1.toString() +"\t" + collection3.toString());

        l1.clear();
        Obj1 o3 = new Obj1(3);
        l1.add(o3);
        o3 = new Obj1(2);
        l1.add(o3);
        o3 = o2;
        l1.add(o3);
        l1.add(o2);
        System.out.println(Collections.frequency(l1, o1));

    }
}

class Obj1 {
    int i;

    public Obj1(int i) {
        this.i = i;
    }
}
