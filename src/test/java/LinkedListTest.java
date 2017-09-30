import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LinkedListTest {
    private List<String> strings;
    private List<Integer> integers;
    private List<Object> emptyList;


    @Before
    public void init() {
        strings = new LinkedList<>();    //ТУТ ІНІЦІАЛІЗУЄМО ЯК LinkedList
        strings.add("string1");
        strings.add("string2");
        strings.add("string3");
        integers = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            integers.add(i);
        }
        emptyList = new LinkedList<>();
    }

    @Test
    public void testAddString() {
        strings.add("string4");

        String expected = "[string1, string2, string3, string4]";
        assertEquals(expected,  Arrays.toString(strings.toArray()));
    }

    @Test
    public void testAddInteger() {
        integers.add(100);

        String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 100]";
        assertEquals(expected,  Arrays.toString(integers.toArray()));
    }

    @Test
    public void testAddEmptyList() {
        emptyList.add(1);
        emptyList.add("string5");

        String expected = "[1, string5]";
        assertEquals(expected,  Arrays.toString(emptyList.toArray()));
    }

    @Test
    public void testRemoveByElementTrue() {
        boolean expected = strings.remove("string2");
        assertTrue(expected);
    }

    @Test
    public void testRemoveByElementFalse() {
        boolean expectedListStringRemove = strings.remove("noElement");
        boolean expectedListStringRemoveNull = strings.remove(null);
        boolean expectedEmpty = emptyList.remove("noElement");
        assertFalse(expectedListStringRemove);
        assertFalse(expectedListStringRemoveNull);
        assertFalse(expectedEmpty);
    }

    @Test
    public void testToArray() {
        String expected = "[string1, string2, string3]";
        assertEquals(expected, Arrays.toString(strings.toArray()));
    }

    @Test
    public void testGet() {
        assertEquals("string1", strings.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsException() {
        try {
            strings.get(10);
            fail();
        } catch (IndexOutOfBoundsException e) {/*NOP*/}
        strings.get(-1);
    }

    @Test
    public void testSet() {
        assertEquals("string1", strings.set(0, "testSet"));
        assertEquals("[testSet, string2, string3]",  Arrays.toString(strings.toArray()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBoundsException() {
        try {
            strings.set(10, "testSet");
            fail();
        } catch (IndexOutOfBoundsException e) {/*NOP*/}
        strings.set(-1, "testSet");
    }
}