import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Shtramak : clear, size, isEmpty, iterator, contains
public class ArrayListTest {

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
    }

    @Test // хз як протестувати цей метод без використання size()
    public void testClear() {
        strings.clear();
        assertEquals(0, strings.size());
        integers.clear();
        assertEquals(0, integers.size());
        emptyList.clear();
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testSize() {
        assertEquals(3, strings.size());
        assertEquals(15, integers.size());
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(emptyList.isEmpty());
        assertFalse(strings.isEmpty());
        assertFalse(integers.isEmpty());
    }git

    @Test
    public void testIterator(){
        String stringsToString = "[String1, String2, String3]";
        String integersToString = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]";
        String emptyListToString = "[]";
        assertEquals(stringsToString, iteratorToString(strings));
        assertEquals(integersToString,iteratorToString(integers));
        assertEquals(emptyListToString,iteratorToString(emptyList));

    }

    @Test
    public void testContains(){
        assertTrue(integers.contains(7));
        assertTrue(strings.contains("String3"));
        assertFalse(strings.contains("String4"));
        assertFalse(emptyList.contains("bla-bla"));
    }

    private <E> String iteratorToString(List<E> list){
       Iterator<E> iterator = list.iterator();
       if (!iterator.hasNext()) return "[]";
       StringBuilder result = new StringBuilder("[");
       while (iterator.hasNext()){
           result.append(iterator.next()).append(", ");
       }
        int startIndex = result.lastIndexOf(", ");
        int endIndex = result.length()-1;
        result.replace(startIndex,endIndex,"]");
        return result.toString().trim();
    }
}