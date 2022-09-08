package chapter_20;

import java.util.*;

public class Ex_03 {
    private static LinkedList<StateCapital> list = new LinkedList<>();

    public static void main(String[] args) {
        setStateCapitals();
        Collections.shuffle(list);

        int correctCount = 0;
        try (Scanner input = new Scanner(System.in)) {
            Iterator<StateCapital> iterator = list.iterator();
            StateCapital currentItem;
            while (iterator.hasNext()) {
                currentItem = iterator.next();
                System.out.print("What is the capital of " + currentItem.getState() + "? ");
                if (currentItem.getCapital().compareToIgnoreCase(input.nextLine().trim()) == 0){
                    System.out.println("Your answer is correct");
                    correctCount++;
                }
                else
                    System.out.println("The correct answer should be " + currentItem.getCapital());
            }
        }
        System.out.print("The correct count is " + correctCount);
    }

    private static void setStateCapitals() {
        Collection<StateCapital> c = Arrays.asList(
                new StateCapital("Alabama","Montgomery"),
                new StateCapital("Alaska","Juneau"),
                new StateCapital("Arizona","Phoenix"),
                new StateCapital("Arkansas","Little Rock"),
                new StateCapital("California","Sacramento"),
                new StateCapital("Colorado","Denver"),
                new StateCapital("Connecticut","Hartford"),
                new StateCapital("Delaware","Dover"),
                new StateCapital("Florida","Tallahassee"),
                new StateCapital("Georgia","Atlanta"),
                new StateCapital("Hawaii","Honolulu"),
                new StateCapital("Idaho","Boise"),
                new StateCapital("Illinois","Springfield"),
                new StateCapital("Indiana","Indianapolis"),
                // To be added, probably
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
//                new StateCapital("",""),
                new StateCapital("Wyoming","Cheyenne")
        );
        list.addAll(c);
    }
}

class StateCapital {
    private String state, capital;

    public StateCapital(String state, String capital) {
        this.state = state;
        this.capital = capital;
    }

    public String getState() {
        return state;
    }

    public String getCapital() {
        return capital;
    }
}
