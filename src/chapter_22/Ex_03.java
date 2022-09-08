package chapter_22;

import java.util.Scanner;

/** (Pattern matching) Write an O(n) time program 
 * that prompts the user to enter two strings 
 * and tests whether the second string is a substring 
 * of the first string. Suppose the neighboring characters 
 * in the string are distinct. 
 * (Don’t use the indexOf method in the String class.)*/
public class Ex_03 {
    public static void main(String[] args) {
        String s1, s2;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a string s1: ");
            s1 = input.nextLine();
            System.out.print("Enter a string s2: ");
            s2 = input.nextLine();
        }

        // matchedIndex determine where the substring match, -1 means it doesn't match.
        // matchedLength determine the current matched length
        int matchedIndex = -1, matchedLength = 0;
        boolean completedMatched = false;
        /* I'm not so sure if time complexity is O(n) in the worst-case. It can be O(mn) tbh.*/
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(matchedLength)) {
                // Update matchedIndex if it's not set, i.e. found the first matching character
                matchedIndex = matchedIndex == -1 ? i : matchedIndex;
                // Update the matchedLength and check if it's the end of the substring, i.e. complete matched
                if (++matchedLength == s2.length()) {
                    completedMatched = true;
                    break;
                }
            } else {
                // Does not match, reset the variables if it was matched before
                if (matchedIndex != -1) {
                    i = matchedIndex + 1;
                    matchedIndex = -1;
                    matchedLength = 0;
                }
            }
        }
        if (completedMatched)
            System.out.print("matched at index " + matchedIndex);
        else
            System.out.print("Mismatch, human is still alive though.");
    }
}
