import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    private List<String> strings;
    private List<Integer> integers;
    private List<Object> emptyList;


    @Before
    public void init() {
        strings = new LinkedList<>();
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
    public void testAddByIndexString() {
        strings.add(0, "string0");
        strings.add(2, "string2");
        strings.add(5, "string5");

        String expected = "[string0, string1, string2, string2, string3, string5]";
        assertEquals(expected, strings.toString());
    }

    @Test
    public void testAddByIndexInteger() {
        integers.add(0, 100);
        integers.add(7, 112);
        integers.add(17, 115);

        String expected = "[100, 0, 1, 2, 3, 4, 5, 112, 6, 7, 8, 9, 10, 11, 12, 13, 14, 115]";
        assertEquals(expected, integers.toString());
    }

    @Test
    public void testAddByIndexEmptyList() {
        emptyList.add(0, 100);

        String expected = "[100]";
        assertEquals(expected, emptyList.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexOutOfBoundsException() {
        try {
            strings.add(-1, "Test22");
            fail(); // if no exception was thrown
        } catch (IndexOutOfBoundsException e) {
            //Catch exception and go forward to check next step
        }
        strings.add(22, "Test22");
    }

    @Test
    public void testAddString() {
        strings.add("string4");

        String expected = "[string1, string2, string3, string4]";
        assertEquals(expected, strings.toString());
    }

    @Test
    public void testAddInteger() {
        integers.add(100);

        String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 100]";
        assertEquals(expected, integers.toString());
    }

    @Test
    public void testAddEmptyList() {
        emptyList.add(1);
        emptyList.add("string5");

        String expected = "[1, string5]";
        assertEquals(expected, emptyList.toString());
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
    public void testRemoveByIndex() {
        String expectedString = strings.remove(1);
        int expectedInt = integers.remove(2);

        assertEquals("string2", expectedString);
        assertEquals(2, expectedInt);
        String expectedStrToString = "[string1, string3]";
        assertEquals(expectedStrToString, strings.toString());

        String expectedIntToString = "[0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";
        assertEquals(expectedIntToString, integers.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIndexIndexOutOfBoundsException() {
        try {
            strings.remove(10);
            fail();
        } catch (IndexOutOfBoundsException e) {/*NOP*/}
        try {
            strings.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {/*NOP*/}
        emptyList.remove(0);
    }

    @Test
    public void testClear() {
        strings.clear();
        assertEquals("[]", strings.toString());
        integers.clear();
        assertEquals("[]", integers.toString());
        emptyList.clear();
        assertEquals("[]", emptyList.toString());
    }

    @Test
    public void testIndexOf() {
        int actual = strings.indexOf("string1");

        assertEquals(0, actual);
    }

    @Test
    public void testIndexOfNoElement() {
        int actual = strings.indexOf("Error");

        assertEquals(-1, actual);
    }
}