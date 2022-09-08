package testingGround;

import java.util.UUID;

public class UUIDconvert {
    public static void main(String[] args) {
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        System.out.println((int)id.getLeastSignificantBits() + " " + (int)id2.getLeastSignificantBits());
        System.out.println((int)id.getMostSignificantBits() + " " + (int)id2.getMostSignificantBits());
    }

}
