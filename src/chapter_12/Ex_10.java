package chapter_12;

public class Ex_10 {
    public static void main(String[] args) {
        try {
            @SuppressWarnings("unused")
            int[] list = new int[2000000000];
        } catch (Error ex) {
            ex.printStackTrace();
            System.out.println("You are running out of memory.");
        }

        System.out.println("GO");

        System.out.println("Wait");
    }
}
