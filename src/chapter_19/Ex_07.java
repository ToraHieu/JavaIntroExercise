package chapter_19;

public class Ex_07 {
    public static void main(String[] args) {
        Transition<Double, Integer, Float> transition = new Transition<>(3.14, 69, (float) 42.0);
        System.out.print(sum(transition));
    }

    public static double sum(Transition<? extends Number, ? extends  Number, ? extends Number> transition) {
        return transition.getO1().doubleValue() + transition.getO2().doubleValue() + transition.getO3().doubleValue();
    }
}
