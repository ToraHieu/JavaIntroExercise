package chapter_12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ex_23 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://cs.armstrong.edu/liang/data/Scores.txt");
            Scanner input = new Scanner(url.openStream());
            int count = 0;
            double total = 0;
            while (input.hasNext()) {
                total+= input.nextDouble();
                count++;
            }
            input.close();
            System.out.println("The total is " + total
                    + "\nThe average is " + (total / count));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
