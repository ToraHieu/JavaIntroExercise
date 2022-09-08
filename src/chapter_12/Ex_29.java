package chapter_12;

import java.io.File;

public class Ex_29 {
    public static void main(String[] args) {
        System.out.println("Usage: *");
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("Excercise[\\d]+_[\\d][.].*")) {
                String s = args[i];
                String newName = s.substring(0, s.indexOf('_') + 1)
                        + "0" + s.substring(s.indexOf('_') + 1);
                File file = new File(args[i]);
                file.renameTo(new File(newName));
                System.out.println("File " + args[i] + " has been changed into " + newName);
            }
        }
        System.out.println("Complete.");
    }
}
