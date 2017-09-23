//Yura
//        add
//        remove(int)
//        indexOf
//        add(int, T)

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayListTest {
    List<String> testStringList = new ArrayList<>();

    @Before
    public void setup() {
        testStringList.add("test1");
        testStringList.add("test2");
        testStringList.add("test3");
    }

    @Test
    public void addTest(){
        testStringList.add("test4");

        String expected = "[test1, test2, test3, test4]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void addByIndexTest(){
        testStringList.add(2, "test5");

        String expected = "[test1, test2, test5, test3, test4]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void removeTest(){
        testStringList.remove(3);

        String expected = "[test1, test2]";

        assertEquals(expected, testStringList.toString());
    }

    @Test
    public void removeTestWithBadIndex(){
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
    public void indexOfTest(){
        int actual = testStringList.indexOf("test1");

        assertEquals(0, actual);
    }

    @Test
    public void indexOfTestNoElement(){
        int actual = testStringList.indexOf("Error");

        assertEquals(-1, actual);
    }
}
