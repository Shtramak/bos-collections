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
}
