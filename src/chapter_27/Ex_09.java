package chapter_27;

public class Ex_09 {
    public static void main(String[] args) {
        String s1 = "tod", s2 = "dot";
        System.out.println(s1.hashCode() + " - " + hashCodeForString(s1));
        System.out.println(s2.hashCode() + " - " + hashCodeForString(s2));
    }

    public static int hashCodeForString(String s) {
        int b = 31, result = 0;
        for (int i = 0; i < s.length(); i++) {
            int unicode = s.charAt(i);
            result = result * b + unicode;
        }

        return result;
    }
}
