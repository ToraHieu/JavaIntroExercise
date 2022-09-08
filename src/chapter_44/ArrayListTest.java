package chapter_44;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArrayListTest {
    private ArrayList<String> list = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {}

    @Test
    public void testInsertion() {
        list.add("Beijing");
        assertEquals("Beijing", list.get(0));
    }
}
