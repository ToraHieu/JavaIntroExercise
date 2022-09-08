package chapter_12;

import java.io.File;

/**
 * Err, since I kind of read the solution file of this at the Excercise12_27
 * already, I'll just copy the solution file here. 
 * Copied from the solution file.
 */

public class Ex_28 {
    public static void main(String[] args) {
        System.out.println("Usage: java Exercise12_28 * ");

        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("Exercise[\\d]_.*")) {
                String s = args[i];
                String newName = s.substring(0, s.indexOf('_') - 1) + "0" + s.substring(s.indexOf('_') - 1);

                File file = new File(args[i]);
                file.renameTo(new File(newName));
            }
        }
    }
}
