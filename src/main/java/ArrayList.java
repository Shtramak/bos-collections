import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int GROW_CAPACITY = DEFAULT_CAPACITY;
    private int size;
    private E[] data;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int size) {
        if (size < 0) throw new IllegalArgumentException("Argument must be positive. Entered argument:" + size);
        data = (E[]) new Object[size];
        GROW_CAPACITY = size;
    }

    //Повернення розміру колекції
    @Override
    public int size() {
        return size;
    }

    //Перевірка колекції на наявність елементів
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     SHTRAMAK: Немає необхідності у створенні окремого внутрішнього класу який би реалізовував інтерфейс Iterator,
     оскільки більше ніде не потрібне посилання на нього, тому повертаємо реалізацію анонімного класу.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int lastReturn = -1;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if(index >= size){
                    throw new IndexOutOfBoundsException();
                }
                lastReturn = index;
                index++;
                return ArrayList.this.get(lastReturn);
            }

            @Override
            public void remove() {
                if(lastReturn<0) throw new IllegalStateException();
                ArrayList.this.remove(lastReturn);
                index = lastReturn;
                lastReturn--;
            }
        };
    }

    @Override
    public <T extends E> boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public <T extends E> boolean remove(T element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (data[0] == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(data[index])) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    //переводим ArrayList в масив
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }

    //Повернення елементу колекції по індексу
    @Override
    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    /*
     SHTRAMAK: Оскільки є привязка до повідомлення, то і на кожну перевірку варто викидати своє
    */
    //Перевірка не вийшов індекс за межі колекції
    private void rangeCheck(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("index =" + index + ", but array size = " + size);
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("index =" + index + ", but must be positive");
        }
    }

    //Заміняємо елемент по індексу
    @Override
    public <T extends E> E set(int index, T element) {
        rangeCheck(index);
        E oldObject = data[index];
        data[index] = element;
        return oldObject;
    }

    //Вилучення елементу по індексу

    /*SHTRAMAK: згодний*/
    //Є окремий метод rangeCheck(index) перевірка індексу в ньому.
    // Лишив свою реалізацію, окрім перевірки (numMoved > 0)
    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        data[--size] = null;
        return oldValue;
    }

    //шукаємо елемнент в смасиві, якщо знайшли то виртаємо його ІНДЕКС
    //якщо не знайшли то -1
    @Override
    public <T extends E> int indexOf(T element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (data[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(data[index])) {
                    return index;
                }
            }
        }
        return -1;
    }

    //Додавання нового елементу в кінець
    @Override
    public <T extends E> boolean add(T element) {
        capacityCheck();
        data[size] = element;
        size++;
        return true;
    }

    //додаємо елемент у вказане місце, переміщачи всі елементи на один в право
    @Override
    public <T extends E> void add(int index, T element) {
        rangeCheck(index);
        capacityCheck();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /*
    SHTRAMAK: Збільшення відбувається не у 2 рази, а у 1.5, оскільки "GROW_CAPACITY >> 1" є аналогом ділення на 2,
    для випадку бітового зсуву вправо "GROW_CAPACITY << 1" збільшення відповідно було б у 3 рази
    */
    //перевіряємо вмістимість масива, якщо достигли максимуму збільшуємо в 2-ва ризи
    private void capacityCheck() {
        if (GROW_CAPACITY <= size + 1) {
            GROW_CAPACITY = GROW_CAPACITY + (GROW_CAPACITY >> 1);
            if (GROW_CAPACITY < 0) {
                throw new OutOfMemoryError();
            }
            if (GROW_CAPACITY == 0){
                GROW_CAPACITY = DEFAULT_CAPACITY;
            }
            data = Arrays.copyOf(data, GROW_CAPACITY);
        }
    }

    //Очистка колекції
    @Override
    public void clear() {
        for (int index = 0; index < size ; index++) {
            data[index] = null;
        }

        size = 0;
    }

    @Override
    public String toString() {
        if (size==0) return "[]";
        StringBuilder result = new StringBuilder("[");
        for (int i=0;i<size;i++){
            result.append(data[i]).append(", ");
        }
        int startIndex = result.lastIndexOf(", ");
        result.replace(startIndex,result.length(),"]");
        return result.toString();
    }
}