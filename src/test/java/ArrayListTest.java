//Yura
//        add
//        remove(int)
//        indexOf
//        add(int, T)

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayListTest {

    @Before
    public void setup() {
        List<String> testList = new ArrayList<String>();
        testList.add("test1");
        testList.add("test2");
        testList.add("test3");
    }

    @Test
    public void addTest(){

        String expected = "[test1, test2, test3]";

        assertEquals(expected, testList.toString());
    }
}
