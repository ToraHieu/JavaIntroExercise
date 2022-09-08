package testingGround;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindDivisors {
    // Driver method
    public static void main(String args[])
    {
        int[] numbers = {420, 523, 516, 520, 476, 593, 516, 515};
        printDivisors(numbers);;

        List<Integer> source = findDivisors(numbers[0]);
        List<List<Integer>> targets = new ArrayList<>();
        for (int i = 1; i < numbers.length; i++) {
            targets.add(findDivisors(numbers[i]));
        }
        removeDuplicated(source, targets);

        System.out.println("Removing duplicated:");
        System.out.println(source);
    }

    static void printDivisors(int[] list) {
        for (int i : list) {
            printDivisors(i);
        }
    }

    static void printDivisors(int n)
    {
        System.out.println(findDivisors(n));
    }

    static List<Integer> findDivisors(int n) {
        List<Integer> list = new ArrayList<>();

        // Note that this loop runs till square root
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
            {
                // If divisors are equal, print only one
                if (n/i != i)
                    list.add(n/i);

                list.add(i);
            }
        }

        list.sort(Comparator.naturalOrder());

        return list;
    }

    static void removeDuplicated(List<Integer> source, List<List<Integer>> targets) {
        for (List<Integer> list: targets) {
            source.removeAll(list);
        }
    }
}
