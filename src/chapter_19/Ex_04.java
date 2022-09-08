package chapter_19;

public class Ex_04 {
    public static void main(String[] args) {
        Pair<ComparableSuperClass> pair = new Pair<>(new ComparableSubClass1(9), new ComparableSubClass2(3.14));
        System.out.print(min(pair).value);
    }

    public static <E extends Comparable<E>> E min(Pair<E> pair) {
        if (pair.getO1().compareTo(pair.getO2()) < 0)
            return pair.getO1();
        else
            return pair.getO2();
    }
}

/** These 3 classes are for testing purpose */
abstract class ComparableSuperClass implements Comparable<ComparableSuperClass> {
    protected double value;
    @Override
    public abstract int compareTo(ComparableSuperClass o);
}

class ComparableSubClass1 extends ComparableSuperClass {
    public ComparableSubClass1(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(ComparableSuperClass o) {
        if (value > o.value)
            return 1;
        else if (value == o.value)
            return 0;
        else
            return -1;
    }
}

class ComparableSubClass2 extends ComparableSuperClass {
    public ComparableSubClass2(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(ComparableSuperClass o) {
        if (value > o.value)
            return 1;
        else if (value == o.value)
            return 0;
        else
            return -1;
    }
}
