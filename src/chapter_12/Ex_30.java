package chapter_12;

import java.io.File;
import java.util.Scanner;

public class Ex_30 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a filename: ");
        String fileName = input.next();
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File does not exist.");
            System.exit(1);
        }
        input.close();
        Scanner scanner = new Scanner(file);
        int[] count = new int[26];
        while (scanner.hasNext()) {
            String s = scanner.next();
            s.toLowerCase();
            char[] ch = s.toCharArray();
            for (char c: ch) {
                if (Character.isLetter(c)) {
                    count[c - 'a']++;
                }
            }
        }
        scanner.close();
        for (int i = 0; i < count.length; i++) {
            System.out.println("Number of " + (char) (i + 'A') + "'s: " + count[i]);
        }
    }
}
