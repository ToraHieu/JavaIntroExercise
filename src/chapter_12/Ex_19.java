package chapter_12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ex_19 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://cs.armstrong.edu/liang/data/Lincoln.txt");
            int count = 0;
            Scanner input = new Scanner(url.openStream());
            while (input.hasNext()) {
                input.next();
                count++;
            }
            input.close();
            System.out.println("There are " + count + " words in the file.");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
