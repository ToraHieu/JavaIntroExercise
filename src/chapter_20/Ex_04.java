package chapter_20;

import java.text.SimpleDateFormat;
import java.util.*;

public class Ex_04 {
    public static void main(String[] args) {
        GregorianCalendar[] calendars = new GregorianCalendar[20];
        for (int i = 0; i < calendars.length; i++) {
            calendars[i] = new GregorianCalendar((int) (Math.random() * 20 + 2000), (int) (Math.random() * 12), (int) (Math.random() * 28));
        }
        ArrayList<GregorianCalendar> list = new ArrayList<>(Arrays.asList(calendars));
        list.forEach(Ex_04::displayGregorianCalendar);
        System.out.println();

        Collections.shuffle(list);
        list.forEach(Ex_04::displayGregorianCalendar);
        System.out.println();

        list.sort(new GregorianCalendarComparator());
        list.forEach(Ex_04::displayGregorianCalendar);
    }

    private static void displayGregorianCalendar(GregorianCalendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        System.out.println(format.format(calendar.getTime()));
    }
}

class GregorianCalendarComparator implements Comparator<GregorianCalendar> {

    @Override
    public int compare(GregorianCalendar o1, GregorianCalendar o2) {
        // If the day is different, return the different
        if (o1.get(GregorianCalendar.DATE) != o2.get(GregorianCalendar.DATE))
            return o1.get(GregorianCalendar.DATE) - o2.get(GregorianCalendar.DATE);
        // Same Date, compare Month next
        if (o1.get(GregorianCalendar.MONTH) != o2.get(GregorianCalendar.MONTH))
            return o1.get(GregorianCalendar.MONTH) - o2.get(GregorianCalendar.MONTH);
        // Same Month, compare Year next
        return o1.get(GregorianCalendar.YEAR) - o2.get(GregorianCalendar.YEAR);
    }
}