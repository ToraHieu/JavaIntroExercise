package chapter_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex_13 {
    private static final int START_YEAR = 2001;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer>[] boysName = (HashMap<String, Integer>[]) new HashMap[10],
                girlsName = (HashMap<String, Integer>[]) new HashMap[10];
        // Get the data
        for (int i = 0; i < 10; i++) {
            boysName[i] = new HashMap<>();
            girlsName[i] = new HashMap<>();
            try (Scanner scanner = new Scanner(new File("babynamesranking" + (START_YEAR + i) +".txt"))) {
                for (int rank = 1; rank <= 1000; rank++) {
                    // Go the the boy name location
                    scanner.next(); scanner.skip("\\s"); // Skip the rank and blank space
                    // Put the boy name and its rank in
                    boysName[i].put(scanner.next(), rank);
                    // Go the the girl name location
                    scanner.skip("\\s"); scanner.next(); scanner.skip("\\s");// Skip the number of boy name and blank space
                    // Put the girl name and its rank in
                    girlsName[i].put(scanner.next(), rank);
                    // Go the next line
                    scanner.nextLine();
                }
            }
        }

        boolean isDone = false;
        short year;
        char gender;
        String name;
        try (Scanner input = new Scanner(System.in)) {
            do {
                System.out.print("Enter the year: ");
                year = input.nextShort();
                if (year < 2001 || year > 2010) {
                    System.out.println("Out side of data range.");
                    continue;
                }
                System.out.print("Enter the gender: ");
                gender = Character.toUpperCase(input.next().charAt(0));
                String sex;
                Map<String, Integer>[] target;
                if (gender == 'M') {
                    sex = "Boy";
                    target = boysName;
                } else if (gender == 'F') {
                    sex = "Girl";
                    target = girlsName;
                } else {
                    System.out.println("Incorrect input.");
                    continue;
                }
                System.out.print("Enter the name: ");
                name = input.next();
                if (target[year - START_YEAR].containsKey(name)) {
                    System.out.println(sex + " name " + name + " is ranked #" +  target[year - START_YEAR].get(name) + " in year " + year);
                } else {
                    System.out.println(sex + " name is not ranked in year " + year);
                }
                System.out.print("Enter another inquiry? ");
                isDone = Character.toUpperCase(input.next().charAt(0)) != 'Y';
            } while (!isDone);
        }
    }
}
