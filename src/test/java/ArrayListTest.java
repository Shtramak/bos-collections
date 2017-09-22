import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {

    private List<String> list;

    @Before
    public void setup(){
        list = new ArrayList<>();
    }

    @Test
    public void add_i_T_Test(){
        for (int i = 0; i < 20; i++) {
            list.add(i,"line" + i);
        }

        assertEquals("[line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, " +
                "line11, line12, line13, line14, line15, line16, line17, line18, line19]", Arrays.toString(list.toArray()));
    }

    @Test
    public void add_T_Test(){
        for (int i = 0; i < 20; i++) {
            list.add("line" + i);
        }

        assertEquals("[line0, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, " +
                "line11, line12, line13, line14, line15, line16, line17, line18, line19]", Arrays.toString(list.toArray()));
    }

    @Test
    public void removeTest(){
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

    @Test
    public void getTest() {
        for (int i = 0; i < 2; i++) {
            list.add("line" + i);
        }

        assertEquals("line0", list.get(0));
    }

    @Test
    public void setTest() {
        for (int i = 0; i < 2; i++) {
            list.add("line" + i);
        }

        assertEquals("line0", list.set(0, "lineTest"));
        assertEquals("[lineTest, line1]", list.toString());
    }
}
