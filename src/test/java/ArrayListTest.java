import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayListTest {

    /*
     Можна зробити String константу для цієї стрічки. Код буде значно читабельнішим
     "[line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, " +
                "line11, line12, line13, line14, line15, line16, line17, line18, line19]"
     Загальне зауваження до іменування тестів. Варто зробити їх переіменування згідно
     загальноприйнятих правил іменування тестів
     Тестові методи зав'язані на методі toArray() з цієї реалізації. Це погано, оскільки,
     якщо цей метод "зламається" то полетять і всі тести повязані зним. Краще скористатись
     методом toString(), оскільки прийнятий формат виводу як правило не підлягає зміні
    */
    private List<String> list;
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

    /*
    У данному тесті ти фактично не перевіряєш основного - коли ти вставляєш елемент на і-ту позицію
    вже існуючого контейнеру.
    Контракт цього методу:
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
    */
    @Test
    public void addWithIndexTest() {
        for (int i = 0; i < 20; i++) {
            list.add(i, "line" + i);
        }

        list.add(0, "Test1");
        list.add(10, "Test10");
        list.add(22, "Test22");


        assertEquals("[Test1, line0, line1, line2, line3, line4, line5, line6, line7, line8, Test10, " +
                "line9, line10, line11, line12, line13, line14, line15, line16, line17, line18, line19, Test22]", list.toString());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void addWithIndexOutOfBoundsExceptionTest() {
        for (int i = 0; i < 20; i++) {
            list.add(i, "line" + i);
        }

        list.add(-1, "Test22");
        list.add(22, "Test22");
    }

    @Test
    public void testAdd() {
        for (int i = 0; i < 20; i++) {
            list.add("line" + i);
        }

        assertEquals("[line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, " +
                "line11, line12, line13, line14, line15, line16, line17, line18, line19]", Arrays.toString(list.toArray()));
    }

    //Даний тест не тестує методу remove()
    @Test
    public void removeTest() {
        for (int i = 0; i < 20; i++) {
            list.add("line" + i);
        }

        assertEquals("[line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, " +
                "line11, line12, line13, line14, line15, line16, line17, line18, line19]", Arrays.toString(list.toArray()));
    }

    @Test
    public void toArrayTest() {
        for (int i = 0; i < 20; i++) {
            list.add("line" + i);
        }

        assertEquals("[line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, " +
                "line11, line12, line13, line14, line15, line16, line17, line18, line19]", Arrays.toString(list.toArray()));
    }

    /*
        Даний тест не покриває ситуацій з вводом некоректних індексів: від'ємного і більшого за поле "size-1" у контейнері.
        Доречі на даний момент це не враховано і у реалізації. Там можна буде отримати або null, або IndexOutOfBoundException
        в залежності від ситуації. Тут доречно в кожному з випадків кидати IllegalArgumentException()
    */
    @Test
    public void getTest() {
        for (int i = 0; i < 2; i++) {
            list.add("line" + i);
        }

        assertEquals("line0", list.get(0));
    }

    /*
    Аналогісна ситуація як і з попереднім тестом. Не покриті ситуації з "поганими" данними...
    Ці тести потрібно розбити на кілька
    */
    @Test
    public void setTest() {
        for (int i = 0; i < 2; i++) {
            list.add("line" + i);
        }

        assertEquals("line0", list.set(0, "lineTest"));
        assertEquals("[lineTest, line1]", list.toString());
    }
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
