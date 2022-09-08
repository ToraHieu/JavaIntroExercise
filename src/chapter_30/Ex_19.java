package chapter_30;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;

public class Ex_19 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            List<Double> list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(scanner.nextDouble());
            }
            DoubleSummaryStatistics stats =
                    list.stream().mapToDouble(Double::doubleValue).summaryStatistics();
        }
    }
}
