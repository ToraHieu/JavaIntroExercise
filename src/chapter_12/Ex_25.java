package chapter_12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ex_25 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://cs.armstrong.edu/liang/data/Salary.txt");
            Scanner input = new Scanner(url.openStream());
            int countAll = 0, countAssistant = 0, countAssociate = 0, countFull = 0;
            float totalAll = 0, totalAssistant = 0, totalAssociate = 0, totalFull = 0;
            while (input.hasNextLine()) {
                // Skip the name of faculties.
                input.next();
                input.next();
                String rank = input.next();
                Double salary = input.nextDouble();
                switch (rank) {
                case "assistant": {
                    countAssistant++;
                    totalAssistant += salary;
                    break;
                }
                case "associate": {
                    countAssociate++;
                    totalAssociate += salary;
                    break;
                }
                case "full": {
                    countFull++;
                    totalFull += salary;
                    break;
                }
                }
                countAll++;
                totalAll+= salary;
            }
            input.close();
            System.out.println("The total salary for assistants are: " + totalAssistant + " and the average are: " + (totalAssistant / countAssistant));
            System.out.println("The total salary for asociates are: " + totalAssociate + " and the average are: " + (totalAssociate / countAssociate));
            System.out.println("The total salary for fulls are: " + totalFull + " and the average are: " + (totalFull / countFull));
            System.out.println("The total salary for all faculty are: " + totalAll + " and the average are: " + (totalAll / countAll));

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
