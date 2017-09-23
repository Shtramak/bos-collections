//Yura
//        add
//        remove(int)
//        indexOf
//        add(int, T)

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayListTest {
    private List<String> testStringList;

    @Before
    public void setup() {
        testStringList = new ArrayList<>();
        testStringList.add("test1");
        testStringList.add("test2");
        testStringList.add("test3");
    }

    @Test
    public void testAdd(){
        testStringList.add("test4");

        String expected = "[test1, test2, test3, test4]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void testAddByIndex(){
        testStringList.add(2, "test5");

        String expected = "[test1, test2, test5, test3]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void testAddByWrongIndex(){
        String actual = "";
        try {
            testStringList.add(20, "test5");
        } catch (IndexOutOfBoundsException e){
            actual += e.getMessage();
        }

        String expected = "get index =20but array size = 3";

        assertEquals(expected, actual);
    }

    @Test
    public void testRemove(){
        testStringList.remove(3);

        String expected = "[test1, test2]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void testRemoveWithBadIndex(){
        String actual = "";
        try {
            testStringList.remove(10);
        } catch (IndexOutOfBoundsException e){
            actual += e.getMessage();
        }

        String expected = "get index =10but array size = 3";

        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOf(){
        int actual = testStringList.indexOf("test1");

        assertEquals(0, actual);
    }

    @Test
    public void testIndexOfNoElement(){
        int actual = testStringList.indexOf("Error");

        assertEquals(-1, actual);
    }
}
