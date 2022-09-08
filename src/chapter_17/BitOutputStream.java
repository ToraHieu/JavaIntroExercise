package chapter_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream {
    private FileOutputStream output;
    private int currentByte = 0;
    private byte counter = 0;

    protected BitOutputStream() {
        this("Exercise17_17.dat"); // Default file name for original exercise
    }

    public BitOutputStream(String fileName) {
        File file = new File(fileName);
        try {
            output = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeBit(char bit) {
        // Check if the bit is valid.
        if (bit == '0' || bit == '1') {
            try {
                currentByte = currentByte << 1;
                currentByte += Character.getNumericValue(bit);
                // When 8 bits are filled, write the byte to the output stream and reset the byte and counter.
                if (++counter == 8) {
                    output.write(currentByte);
                    currentByte = 0;
                    counter = 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeBit(String bit) {
        for (char c : bit.toCharArray()) {
            writeBit(c);
        }
    }

    public void close() {
        // Check if the byte is not empty, i.e., not 0
        if (currentByte != 0) {
            try {
                currentByte = currentByte << (8 - counter);
                output.write(currentByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class BitOutputStream {
//    /** Attributes */
//    private FileOutputStream output;
//    private int value;
//    private int count = 0;
//    private int mask = 1; // The bits are all zeros except the last one
//
//    /** Constructor */
//    public BitOutputStream(File file, boolean append) throws IOException {
//        output = new FileOutputStream(file, append);
//    }
//
//    /** Overloaded write bit function */
//    public void writeBit(char bit) throws IOException {
//        count++;
//        value = value << 1;
//
//        if (bit == '1')
//            value = value | mask;
//
//        if (count == 8) {
//            output.write(value);
//            count = 0;
//        }
//    }
//
//    public void writeBit(String bitString) throws IOException {
//        for (int i = 0; i < bitString.length(); i++)
//            writeBit(bitString.charAt(i));
//    }
//
//    /**
//     * Write the last byte and close the stream. If the last byte is not full,
//     * right-shift with zeros
//     */
//    public void close() throws IOException {
//        if (count > 0) {
//            value = value << (8 - count);
//            output.write(value);
//        }
//
//        output.close();
//    }
//}