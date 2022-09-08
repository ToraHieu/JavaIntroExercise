package chapter_18;

public class Ex_25 {
    /** Display all the permutations of a string.
     * Will display them sorted if the provided string is sorted.
     * */
    public static void displayPermutation(String s) {
        displayPermutation("", s);
    }

    public static void displayPermutation(String s1, String s2) {
        if (s2.length() == 0) {
            System.out.println(s1);
            return;
        }

        // It's magic. No need to ask why. No need to understand how. Just pure magic.
        // JK, JK, It works like this
        // A loop to put each character of s2 to s1 and then recursively call it self with the new s1 and s2
        // (while actually keeping the old s1 and s2 for the next iteration.)
        for (int i = 0; i < s2.length(); i++) {
            displayPermutation(s1 + s2.charAt(i), removeChar(s2, i));
        }
    }

    public static void main(String[] args) {
        displayPermutation("abcd");
    }

    public static String removeChar(String s, int index) {
        return s.substring(0, index).concat(s.substring(index + 1));
    }
}
