package chapter_10;

public class MyString1 {
    public static void main(String[] agrs) {
        MyString1 s1 = new MyString1(new char[] {'j', 'a', 'v', 'a'});
        System.out.println(s1.length());
        System.out.println(s1.charAt(2));
        System.out.println(s1.equals(new MyString1(new char[] {'j', 'a', 'v', 'a'})));
        MyString1 s2 = MyString1.valueOf(123);
        for (int i = 0;  i < s2.length(); i++) {
            System.out.print(s2.charAt(i));
        }
    }
    
    private char[] realString;
    
    public MyString1 (char[] chars) {
        realString = new char[chars.length];
        for (int i = 0; i < realString.length; i++) {
            realString[i] = chars[i];
        }
    }
    
    public char charAt(int index) {
        return realString[index];
    }
    
    public int length() {
        return realString.length;
    }
    
    public MyString1 substring(int begin, int end) {
        int substringLength = end - begin;
        char[] subMyString = new char[substringLength];
        for (int i = 0; i < substringLength; i++) {
            subMyString[i] = realString[i + begin];
        }
        return new MyString1(subMyString);
    }
    
    public MyString1 toLowerCase() {
        char[] newChars = new char[realString.length];
        for (int i = 0; i < newChars.length; i++) {
            if (realString[i] >= 65 && realString[i] <= 90) 
                newChars[i] =(char) (realString[i] + 33);
            else 
                newChars[i] = realString[i];
        }
        return new MyString1(newChars);
    }
    
    public boolean equals(MyString1 s) {
        if (realString.length != s.length()) 
                return false;
        
        for (int i = 0; i < realString.length; i++) {
            if (realString[i] != s.charAt(i)) 
                return false;
        }
        
        return true;
    }
    
    public static MyString1 valueOf(int i) {
        char[] temp1 = new char[40];
        int size = 0;
        while (i != 0) {
            int number = i % 10;
            i /= 10;
            temp1[size] = (char) (number + '0');
            size++;
        }
        
        char[] temp2 = new char[size];
        for (int k = size - 1; k >= 0; k--) {
          temp2[k] = temp1[size - k - 1];
        }
        
        return new MyString1(temp2);
    }

}
