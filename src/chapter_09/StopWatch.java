package chapter_09;
// For Ex_06
public class StopWatch {
	private long startTime, endTime;
	
	public StopWatch() {
		startTime = System.currentTimeMillis();
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		endTime = System.currentTimeMillis();
	}
	
	public long getElapsedTime() {
		return endTime - startTime;
	}

}
