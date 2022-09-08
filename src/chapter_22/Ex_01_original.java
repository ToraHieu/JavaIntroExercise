package chapter_22;

/** (Maximum consecutive increasingly ordered substring)
 * Write a program that prompts the user to enter a string
 * and displays the maximum consecutive increasingly ordered substring.
 * Analyze the time complexity of your program.*/
public class Ex_01_original {
    public static void main(String[] args) {

        String s = "abcabcdgabmnsxy";
        int maxStartIndex = 0, maxLength = 0, currentStartIndex = 0, currentLength = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            // This is not a strictly increasingly ordered, if it is, there are a few changes to be made
            if (chars[i] > chars[i+1]) {
                // Consecutive increasingly ordered substring ended here
                currentStartIndex = i + 1;
                currentLength = 1;
            } else {
                // If the current substring is the maximum, update its length and check the next letter
                if (maxStartIndex == currentStartIndex) {
                    maxLength = ++currentLength;
                    continue;
                }
                //  Update the length and check if it's a new maximum
                if (++currentLength > maxLength) {
                    // Found a longer substring, update the start and length
                    maxStartIndex = currentStartIndex;
                    maxLength = currentLength;
                }
            }
        }
        String result = s.substring(maxStartIndex, maxStartIndex + maxLength);
        System.out.print(result);
    }
}
