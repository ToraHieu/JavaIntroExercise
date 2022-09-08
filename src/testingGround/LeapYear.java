package testingGround;

public class LeapYear {
    public static void main(String[] args) {
        int y = (int) (Math.random() * 100);
        System.out.print("Is " + y + " leap year: " + (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)));
    }
}
