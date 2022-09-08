package chapter_25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex_16 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        File file = new File(input.next());
        while (!file.isFile()) {
            System.out.print("File not found.\nPlease try again: ");
            file = new File(input.next());
        }
        StringBuilder text = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
                text.append(fileScanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[] counts = HuffmanCode.getCharacterFrequency(text.toString()); // Count frequency

        HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts); // Create a Huffman tree
        if (tree == null || tree.root == null)
            System.out.print("File is unreadable in ASCII encoder or is empty.");
        else {
            System.out.printf("%-15s%-15s%-15s%-15s\n",
                    "ASCII Code", "Character", "Frequency", "Code");

            String[] codes = HuffmanCode.getCode(tree.root); // Get codes

            for (int i = 0; i < codes.length; i++)
                if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
                    System.out.printf("%-15d%-15s%-15d%-15s\n",
                            i, (char) i + "", counts[i], codes[i]);
        }
    }
}
