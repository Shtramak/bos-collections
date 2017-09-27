import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ArrayListTest {
    private List<String> strings;
    private List<Integer> integers;
    private List<Object> emptyList;


    @Before
    public void init() {
        strings = new ArrayList<>();
        strings.add("string1");
        strings.add("string2");
        strings.add("string3");
        integers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            integers.add(i);
        }
        emptyList = new ArrayList<>(0);
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
    public void addWithIndexOutOfBoundsExceptionTest() {
        strings.add(-1, "Test22");
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

    /*
    SHTRAMAK: до тесту strings.remove(-1), та emptyList.remove(0) не дійде, перевірка тесту завершиться на стадії
    strings.remove(10), після того, як метод викине виключення IndexOutOfBoundsException, і завершиться успіхом
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIndexIndexOutOfBoundsException() {
        strings.remove(10);
        strings.remove(-1);
        emptyList.remove(0);
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

    /*
    SHTRAMAK: перевірка другої умови assertEquals не відбудеться. Для чого тут взагалі "assertEquals"? Копіпаст )
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsException() {
        assertEquals("string1", strings.get(10));
        assertEquals("string1", strings.get(-1));
    }

    @Test
    public void testSet() {
        assertEquals("string1", strings.set(0, "testSet"));
        assertEquals("[testSet, string2, string3]", strings.toString());
    }

    /*
    SHTRAMAK: перевірка друго виклику методу set не відбудеться.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBoundsException() {
        strings.set(10, "testSet");
        strings.set(-1, "testSet");
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
        String stringsToString = "[string1, string2, string3]";
        String integersToString = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";
        String emptyListToString = "[]";
        assertEquals(stringsToString, iteratorToString(strings));
        assertEquals(integersToString, iteratorToString(integers));
        assertEquals(emptyListToString, iteratorToString(emptyList));

    }

    @Test
    public void testContains() {
        assertTrue(integers.contains(7));
        assertTrue(strings.contains("string3"));
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
        int actual = strings.indexOf("string1");

        assertEquals(0, actual);
    }

    @Test
    public void testIndexOfNoElement() {
        int actual = strings.indexOf("Error");

        assertEquals(-1, actual);
    }
}
