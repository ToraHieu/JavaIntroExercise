package chapter_18;

public class Ex_23 {
    public static int bin2Dec(String binaryString) {
        return bin2Dec(binaryString, 0, 0);
    }

    private static int bin2Dec(String binaryString, int index, int result) {
        if (index == binaryString.length())
            return result;

        int bin = Character.getNumericValue(binaryString.charAt(binaryString.length() - 1 - index));
        result += bin * (int) Math.pow(2, index);
        return bin2Dec(binaryString, index + 1, result);
    }

    public static void main(String[] args) {
        System.out.println(bin2Dec("11000000"));
    }
}
