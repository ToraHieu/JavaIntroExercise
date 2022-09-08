package chapter_12;

@SuppressWarnings("serial")
public class BinaryFormatException extends Exception {
    public BinaryFormatException() {
        super("Not a binary number");
    }
    
    public BinaryFormatException(String message) {
        super(message);
    }
}
