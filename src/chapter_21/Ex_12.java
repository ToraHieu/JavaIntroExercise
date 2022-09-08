package chapter_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ex_12 {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a file name for baby name ranking: ");
            fileName = input.next();
        }
        Set<String> boyName = new HashSet<>(),
                girlName = new HashSet<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            for (int rank = 1; rank <= 1000; rank++) {
                // Go the the boy name location
                scanner.next(); scanner.skip("\\s"); // Skip the rank and blank space
                // Put the boy name and its rank in
                boyName.add(scanner.next());
                // Go the the girl name location
                scanner.skip("\\s"); scanner.next(); scanner.skip("\\s");// Skip the number of boy name and blank space
                // Put the girl name and its rank in
                girlName.add(scanner.next());
                // Go the next line
                scanner.nextLine();
            }
        }

        // Intersect sets
        boyName.retainAll(girlName);

        System.out.println(boyName.size() + " names used for both genders");
        System.out.println("They are ");
        boyName.forEach(System.out::println);
    }
}
