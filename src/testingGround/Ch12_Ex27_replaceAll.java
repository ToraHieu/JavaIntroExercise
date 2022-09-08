package testingGround;

public class Ch12_Ex27_replaceAll {
    public static void main(String[] args) {
        String s = "Excercise1_1 Excercise1_2 Test Test test1_2";
        String s2 = s.replaceAll("Excercise[\\d]_[\\d]", "Excercise0d_0d");
        System.out.println(s2);
    }
}
