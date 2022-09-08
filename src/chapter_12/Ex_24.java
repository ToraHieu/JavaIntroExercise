package chapter_12;

import java.io.File;
import java.io.PrintWriter;

public class Ex_24 {
    public static void main(String[] args) throws Exception {
        File file = new File("Salary.txt");
        if (file.exists()) {
            System.out.println("The file has already existed.");
            System.exit(1);
        }
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= 1000; i++) {
            String rank = generateRank();
            double salaryDouble = generateSalary(rank);
            String salary = String.format("%.2f", salaryDouble);
            content.append("FirstName" + i + " LastName" + i
                    + " " + rank + " " + salary + "\r\n");
        }
        try (PrintWriter output = new PrintWriter(file)) {
            output.print(content);
        }
    }

    private static String generateRank() {
        int rank = (int) (Math.random() * 3);
        switch (rank) {
        case 1: return "assistant";
        case 2: return "associate";
        default: return "full";
        }
    }
    
    private static Double generateSalary(String rank) {
        switch (rank) {
        case "assistant": return Math.random() * (80000 - 50000) + 50000;
        case "associate": return Math.random() * (110000 - 60000) + 60000;
        default: return Math.random() * (130000 - 75000) + 75000;
        }
    }
}
