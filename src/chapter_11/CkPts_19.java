package chapter_11;

public class CkPts_19 {
    public static void main(String[] agrs) {
        /* This code will result an error.
         * Object[] i = new int[50];
         */
        Object[] o = new Object[50];
        o[0] = "test";
        System.out.println(o[0].toString());
    }

}
