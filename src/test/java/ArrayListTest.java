
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class ArrayListTest {
    private List<String> testStringList;
    private List<String> strings;
    private List<Integer> integers;
    private List<Object> emptyList;

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

        strings = new ArrayList<>();
        strings.add("String1");
        strings.add("String2");
        strings.add("String3");

        integers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            integers.add(i);
        }

        emptyList = new ArrayList<>(0);
    }

    @Test
    public void testAddByIndexString() {
        testStringList.add(0, "test0");
        testStringList.add(2, "test2");
        testStringList.add(5, "test5");

        String expected = "[test0, test1, test2, test2, test3, test5]";
        assertEquals(expected, testStringList.toString());
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
    public void addWithIndexOutOfBoundsExceptionTest() {
        testStringList.add(-1, "Test22");
        testStringList.add(22, "Test22");
    }

    @Test
    public void testAddString() {
        testStringList.add("test4");

        String expected = "[test1, test2, test3, test4]";
        assertEquals(expected, testStringList.toString());
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
        emptyList.add("test5");

        String expected = "[1, test5]";
        assertEquals(expected, emptyList.toString());
    }

    @Test
    public void testRemoveByElementTrue() {
        boolean expected = testStringList.remove("test2");
        assertTrue(expected);
    }

    @Test
    public void testRemoveByElementFalse() {
        boolean expectedListStringRemove = testStringList.remove("noElement");
        boolean expectedListStringRemoveNull = testStringList.remove(null);
        boolean expectedEmpty = emptyList.remove("noElement");
        assertFalse(expectedListStringRemove);
        assertFalse(expectedListStringRemoveNull);
        assertFalse(expectedEmpty);
    }

    @Test
    public void testRemoveByIndex() {
        String expectedString = testStringList.remove(1);
        int expectedInt = integers.remove(2);

        assertEquals("test2", expectedString);
        assertEquals(2, expectedInt);
        String expectedStrToString = "[test1, test3]";
        assertEquals(expectedStrToString, testStringList.toString());

        String expectedIntToString = "[0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";
        assertEquals(expectedIntToString, integers.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIndexIndexOutOfBoundsException() {
        testStringList.remove(10);
        testStringList.remove(-1);
        emptyList.remove(0);
    }

    @Test
    public void testToArray() {
        String expected = "[test1, test2, test3]";
        assertEquals(expected, Arrays.toString(testStringList.toArray()));
    }

    @Test
    public void testGet() {
        assertEquals("test1", testStringList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsException() {
        assertEquals("test1", testStringList.get(10));
        assertEquals("test1", testStringList.get(-1));
    }

    @Test
    public void testSet() {
        assertEquals("test1", testStringList.set(0, "testSet"));
        assertEquals("[testSet, test2, test3]", testStringList.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBoundsException() {
        testStringList.set(10, "testSet");
        testStringList.set(-1, "testSet");
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

        assertEquals(integersToString,iteratorToString(integers));
        assertEquals(emptyListToString,iteratorToString(emptyList));
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
