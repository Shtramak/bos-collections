import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayListTest {

    private List<String> strings;
    private List<Integer> integers;
    private List<Object> emptyList;
    private List<String> testStringList;


    @Before
    public void init() {
        strings = new ArrayList<>();
        strings.add("String1");
        strings.add("String2");
        strings.add("String3");
        integers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            integers.add(i);
        }
        emptyList = new ArrayList<>(0);
        testStringList = new ArrayList<>();
        testStringList.add("test1");
        testStringList.add("test2");
        testStringList.add("test3");

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
    public void testSize() {
        assertEquals(3, strings.size());
        assertEquals(15, integers.size());
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyList.isEmpty());
        assertFalse(strings.isEmpty());
        assertFalse(integers.isEmpty());
    }

    @Test
    public void testIterator() {
        String stringsToString = "[String1, String2, String3]";
        String integersToString = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";
        String emptyListToString = "[]";
        assertEquals(stringsToString, iteratorToString(strings));
        assertEquals(integersToString, iteratorToString(integers));
        assertEquals(emptyListToString, iteratorToString(emptyList));

    }

    @Test
    public void testContains() {
        assertTrue(integers.contains(7));
        assertTrue(strings.contains("String3"));
        assertFalse(strings.contains("String4"));
        assertFalse(emptyList.contains("bla-bla"));
    }

    private <E> String iteratorToString(List<E> list) {
        Iterator<E> iterator = list.iterator();
        if (!iterator.hasNext()) return "[]";
        StringBuilder result = new StringBuilder("[");
        while (iterator.hasNext()) {
            result.append(iterator.next()).append(", ");
        }
        int startIndex = result.lastIndexOf(", ");
        int endIndex = result.length() - 1;
        result.replace(startIndex, endIndex, "]");
        return result.toString().trim();
    }

    @Test
    public void testAdd() {
        testStringList.add("test4");

        String expected = "[test1, test2, test3, test4]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void testAddByIndex() {
        testStringList.add(2, "test5");

        String expected = "[test1, test2, test5, test3]";

        assertEquals(expected, testStringList.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddByWrongIndex() {
        String actual = "";
        testStringList.add(20, "test5");
    }

    @Test
    public void testRemove() {
        testStringList.remove(3);

        String expected = "[test1, test2]";

        assertEquals(expected, testStringList.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithBadIndex() {
        String actual = "";
        testStringList.remove(10);
    }

    @Test
    public void testIndexOf() {
        int actual = testStringList.indexOf("test1");

        assertEquals(0, actual);
    }

    @Test
    public void testIndexOfNoElement() {
        int actual = testStringList.indexOf("Error");

        assertEquals(-1, actual);
    }
}