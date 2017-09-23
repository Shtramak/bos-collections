//Yura
//        add
//        remove(int)
//        indexOf
//        add(int, T)

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArrayListTest {
    //IDEA сама підказує що тут можна заюзати ідентифікатор private. Треба себе до цього привчати... Інкапсуляція ж як ніяк )))
    //Саму ініціалізацію поля краще перенести у setup(), так само як поля краще ініціалізувати у конструкторі.
    List<String> testStringList = new ArrayList<>();

    @Before
    public void setup() {
        testStringList.add("test1");
        testStringList.add("test2");
        testStringList.add("test3");
    }

    //Ім'я addTest() варто замінити на testAdd() згідно загальноприйнятих правил іменування тестів
    @Test
    public void addTest(){
        testStringList.add("test4");

        String expected = "[test1, test2, test3, test4]";

        assertEquals(expected, testStringList.toString());
    }
}