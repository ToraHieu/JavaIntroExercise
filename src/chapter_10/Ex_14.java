package chapter_10;

public class Ex_14 {
    public static void main(String[] agrs) {
        MyDate today = new MyDate();
        System.out.println(today.getYear() + "/" + today.getMonth() + "/" + today.getDay());
        
        MyDate day1 = new MyDate(561555550000L);
        System.out.println(day1.getYear() + "/" + day1.getMonth() + "/" + day1.getDay());

    }

}
