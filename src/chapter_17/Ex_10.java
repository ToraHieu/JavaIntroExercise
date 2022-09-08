package chapter_17;

import java.io.*;

public class Ex_10 {

    public static void main(String[] args) throws IOException {
        /* Reserved code for testing
        // Stimulate the command input, assuming all the input is valid
        args = new String[]{"SourceFile.txt", "7"};
        */

        File sourceFile = new File(args[0]);
        int numberOfPieces = Integer.parseInt(args[1]);
        int numberOfBytesPerFile = (int)(Math.ceil((double)sourceFile.length() / numberOfPieces));

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile))) {
            for (int i = 1; i <= numberOfPieces; i++) {
                byte[] bytes = new byte[numberOfBytesPerFile];
                int bytesWritten = inputStream.read(bytes);
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(args[0] + "." + i))) {
                    if (bytesWritten == numberOfBytesPerFile)
                        outputStream.write(bytes);
                    else
                        outputStream.write(bytes, 0, bytesWritten);
                }
            }
        }
}
}
