package chapter_10;

public class Ex_01 {
	public static void main(String[] agrs) {
		Time t1 = new Time();
		System.out.println(t1.getHour() + ":" + t1.getMinute() + ":" + t1.getSecond());
		
		Time t2 = new Time(555550000);
		System.out.println(t2.getHour() + ":" + t2.getMinute() + ":" + t2.getSecond());

	}

}
