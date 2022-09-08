package chapter_25;

import java.io.*;
import java.util.Scanner;

public class Ex_19 {
    public static void main(String[] args) {
        String sourcefile = "src/chapter_25/TargetFile.dat", targetfile = "src/chapter_25/DecompressedFile.txt";

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
        int[] counts = new int[0]; // Blank initializer
        long position = 0;
        int unusedBits = 0;
        try (FileInputStream fileInputStream = new FileInputStream(sourcefile)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream((fileInputStream))) {
                counts = (int[]) objectInputStream.readObject(); // Get the counts object for character frequency
                unusedBits = objectInputStream.readInt();
                position = fileInputStream.getChannel().position(); // Get the current position of the file reader.
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder compressedContent = new StringBuilder();

        // The remain of the file is Huffman code
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourcefile))) {
            if (inputStream.skip(position) == position) {
                int value;
                while ((value = inputStream.read()) != -1) {
                    compressedContent.append(String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0'));
                }
                compressedContent.delete(compressedContent.length() - unusedBits, compressedContent.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts); // Reconstruct a Huffman tree

        // Decompress the codes into data using Huffman tree
        int length = compressedContent.length();
        HuffmanCode.Tree.Node currentNode = tree.root;
        StringBuilder decompressedContent = new StringBuilder();
        for (int i = 0; i < length;) {
            if (currentNode.left == null && currentNode.right == null) {
                decompressedContent.append(currentNode.element);
                currentNode = tree.root;
                continue;
            }

            if (compressedContent.charAt(i++) == '0')   // Use the current code for to travel to the next node
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }

        try (PrintWriter printWriter = new PrintWriter(target)) {
            printWriter.print(decompressedContent.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
