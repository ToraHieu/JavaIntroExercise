package chapter_18;

public class Ex_22 {
    public static String dec2Hex(int value) {
        return dec2Hex(value, "");
    }

    private static String dec2Hex(int value, String result) {
        int dec = value % 16;
        value /= 16;
        char hex;
        switch (dec) {
            case 10: hex = 'A'; break;
            case 11: hex = 'B'; break;
            case 12: hex = 'C'; break;
            case 13: hex = 'D'; break;
            case 14: hex = 'E'; break;
            case 15: hex = 'F'; break;
            default: hex = (char) (dec + '0');
        }
        result = hex + result ;
        if (value == 0)
            return result;
        return dec2Hex(value, result);
    }

    public static void main(String[] args) {
        System.out.println(dec2Hex(65535));
    }
}
