import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public abstract class AbstractList {
    private List<String> strings;
    private List<Integer> integers;
    private List<Object> emptyList;

    protected abstract List<String> getStringList();

    protected abstract List<Integer> getIntegerList();

    protected abstract List<Object> getEmptyList();

    @Before
    public void init() {
        this.strings = getStringList();
        this.integers = getIntegerList();
        this.emptyList = getEmptyList();

        strings.add("string1");
        strings.add("string2");
        strings.add("string3");
        for (int i = 0; i < 15; i++) {
            integers.add(i);
        }
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
            fail();
        } catch (IndexOutOfBoundsException e) {/*NOP*/}
        strings.add(22, "Test22");
    }

    @Test
    public void testAddString() {
        strings.add("string4");

        String expected = "[string1, string2, string3, string4]";
        assertEquals(expected, Arrays.toString(strings.toArray()));
    }

    @Test
    public void testAddInteger() {
        integers.add(100);

        String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 100]";
        assertEquals(expected, Arrays.toString(integers.toArray()));
    }

    @Test
    public void testAddEmptyList() {
        emptyList.add(1);
        emptyList.add("string5");

        String expected = "[1, string5]";
        assertEquals(expected, Arrays.toString(emptyList.toArray()));
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
    public void testRemoveNullElement() {
        emptyList.add(null);
        emptyList.add(new Object());
        assertTrue(emptyList.remove(null));
    }

    @Test
    public void testToArray() {
        String expected = "[string1, string2, string3]";
        assertEquals(expected, Arrays.toString(strings.toArray()));
        assertEquals("[]", Arrays.toString(emptyList.toArray()));
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
        assertEquals("[testSet, string2, string3]", Arrays.toString(strings.toArray()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBoundsException() {
        try {
            strings.set(10, "testSet");
            fail();
        } catch (IndexOutOfBoundsException e) {/*NOP*/}
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
    public void testContains() {
        assertTrue(integers.contains(7));
        assertTrue(strings.contains("string3"));
        assertFalse(strings.contains("String4"));
        assertFalse(emptyList.contains("bla-bla"));
    }

    @Test
    public void testIteratorNextAndHasNext() {
        String stringsToString = "[string1, string2, string3]";
        String integersToString = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";
        String emptyListToString = "[]";
        assertEquals(stringsToString, iteratorToString(strings));
        assertEquals(integersToString, iteratorToString(integers));
        assertEquals(emptyListToString, iteratorToString(emptyList));

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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorNextWithException() {
        Iterator iterator = emptyList.iterator();
        iterator.next();
    }

    @Test
    public void testIteratorRemove() {
        String stringsToString = "[string1, string3]";
        String integersToString = "[0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14]";
        removeIteratorElement(strings, "string2");
        removeIteratorElement(integers, 6);
        assertEquals(stringsToString, iteratorToString(strings));
        assertEquals(integersToString, iteratorToString(integers));
    }

    private <E> void removeIteratorElement(List<E> list, E element) {
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            E currentElement = iterator.next();
            if (currentElement.equals(element))
                iterator.remove();
        }
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

    @Test
    public void testIndexOfWithNullElement() {
        emptyList.add(new Object());
        emptyList.add(new Object());
        emptyList.add(null);
        emptyList.add(new Object());
        assertEquals(2, emptyList.indexOf(null));
    }
}