import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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

    /*Тут ще можна проініціалізувати тестовий контейнер для не add методів, щоб не дублювати
    постійно
    for (int i = 0; i < 20; i++) {
        list.add("line" + i);
    }
    Або винести генерацію тестових даних у окремий метод getTestData()
    */
    @Before
    public void setup() {
        list = new ArrayList<>();
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
