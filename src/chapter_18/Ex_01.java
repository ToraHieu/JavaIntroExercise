package chapter_18;

public class Ex_01 {
    public static void main(String[] args) {
        System.out.println(isAnagrams("ADCSSSS","SSSACSD"));
    }

    public static boolean isAnagrams(String s1, String s2) {
        // They're obviously not Anagrams if their length are different
        if (s1.length() != s2.length())
            return false;

        return isAnagramsRecursively(s1, s2);
    }

    private static boolean isAnagramsRecursively(String s1, String s2) {
        if (s1.length() == 0) // Base case
            return true;
        // Take the first character of s1 and find it in the s2
        int firstCharIndexInS2 = s2.indexOf(s1.charAt(0));
        if (firstCharIndexInS2 == -1)
            return false;
        else // Reduce the string s1 and s2 by removing the character mentioned in both string.
            return isAnagramsRecursively(s1.substring(1), s2.substring(0, firstCharIndexInS2).concat(s2.substring(firstCharIndexInS2+1)));
    }
}
