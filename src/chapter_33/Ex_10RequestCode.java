package chapter_33;

import java.util.HashMap;

public enum Ex_10RequestCode {
    SEND_MESSAGE (201), 
    RECEIVE_MESSAGE (202),
    ERROR_SEND (401),
    ERROR_RECEIVE (402),
    REQUEST_ALL_MESSAGE (601),
    DISCONNECT (9000);

    private final int value;

    Ex_10RequestCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static final HashMap<Integer, Ex_10RequestCode> lookup = new HashMap<>();

    static {
        for (Ex_10RequestCode c: Ex_10RequestCode.values()) {
            lookup.put(c.value, c);
        }
    }

    public static Ex_10RequestCode get(int value) {
        return lookup.get(value);
    }
}
