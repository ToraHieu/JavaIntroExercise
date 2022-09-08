package chapter_09;

public class Location {
	private int row;
	private int column;
	private double maxValue;
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public double getMaxValue() {
		return maxValue;
	}
	
	public void setRow(int newRow) {
		row = newRow;
	}
	
	public void setColumn(int newColumn) {
		column = newColumn;
	}
	
	public void setMaxValue(double newMaxValue) {
		maxValue = newMaxValue;
	}
	
	public String toString(){
		String s = "The maximum value " + maxValue + " is located in [" + row +", " + column +"].";
		return s;
	}
}
