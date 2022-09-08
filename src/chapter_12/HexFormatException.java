package chapter_12;

@SuppressWarnings("serial")
public class HexFormatException extends Exception {
    public HexFormatException(String message) {
        super(message);
    }
    
    public HexFormatException() {
        super("Not a hex value.");
    }

}
