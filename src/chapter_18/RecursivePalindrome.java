package chapter_18;

public class RecursivePalindrome {
    public static boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1);
    }

    private static boolean isPalindrome(String s, int low, int high) {
        if (high <= low) // Base class
            return true;
        else if (s.charAt(low) != s.charAt(high)) //
            return false;
        else
            return isPalindrome(s, low + 1, high - 1);
    }

    public static void main(String[] args) {
        System.out.println("Is moon a palindrome? " + isPalindrome("moon"));
        System.out.println("Is noon a palindrome? " + isPalindrome("noon"));
        System.out.println("is a a palindrome? " + isPalindrome("a"));
        System.out.println("is aba a palindrome? " + isPalindrome("aba"));
        System.out.println("is ab a palindrome? " + isPalindrome("ab"));
    }
}
