package chapter_25;

import chapter_17.BitOutputStream;

import java.io.*;
import java.util.Scanner;

public class Ex_18 {
    public static void main(String[] args) {
        String sourcefile = "src/chapter_25/SourceFile.txt", targetfile = "src/chapter_25/TargetFile.dat";

        File source = new File(sourcefile);
        if (!source.exists() || !source.isFile()) {
            System.out.print("Source file does not exist. Program is exiting.");
            System.exit(0);
        }
        File target = new File(targetfile);
        if (target.exists() && target.isFile()) {
            System.out.print("Target file already exists. Overwrite the file permanently?" +
                    "\nYes(Y): ");
            char answer = new Scanner(System.in).next().charAt(0);
            if (Character.toUpperCase(answer) != 'Y') {
                System.out.print("Target file already exists and not to be overwritten. Program is exiting.");
                System.exit(0);
            } else {
                System.out.print("Target file will be overwritten once the process completed. Do not open it until then.");
            }
        } else {
            try {
                if (target.createNewFile()) {
                    System.out.print("Target file does not exist. File created successfully.");
                } else {
                    System.out.print("Target file does not exist. File was not created. Please try again.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder text = new StringBuilder();
        try (Scanner fileScanner = new Scanner(source)) {
            while (fileScanner.hasNextLine())
                text.append(fileScanner.nextLine()).append('\n');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[] counts = HuffmanCode.getCharacterFrequency(text.toString()); // Count frequency

        HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts); // Create a Huffman tree
        if (tree == null || tree.root == null)
            System.out.print("File is unreadable in ASCII encoder or is empty.");
        else {
            String[] codes = HuffmanCode.getCode(tree.root); // Get codes
            StringBuilder bits = new StringBuilder();
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                String code = codes[(int)text.charAt(i)];
                bits.append(code);
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                objectOutputStream.writeObject(counts);
                int unusedBits = 8 - bits.length() % 8; // Number of unused bit in the last byte, 8 = zero
                objectOutputStream.writeInt(unusedBits == 8 ? 0 : unusedBits);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BitOutputStream bitOutputStream = new BitOutputStream(targetfile);
            bitOutputStream.writeBit(bits.toString());
            bitOutputStream.close();
        }
    }
}
