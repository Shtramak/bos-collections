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
        this.size = size;
        data = (E[]) new Object[size];
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

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T extends E> boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public <T extends E> boolean remove(T element) {
        return false;
    }

    //переводим ArrayList в масив
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        return (E[]) Arrays.copyOf(data, size);
    }

    //Повернення елементу колекції по індексу
    @Override
    public E get(int index) {
       rangeCheck(index);

       return data[index];
    }

    //Перевірка не вийшов індекс за межі колекції
    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("get index =" + index + "but array size = " + size);
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

    //Додавання нового елементу в кінець
    @Override
    public <T extends E> boolean add(T element) {
        capacityCheck();
        data[size++] = element;
        return true;
    }

    //Вилучення елементу по індексу
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = data[index];
        int numMoved = size - index - 1;
        System.arraycopy(data, index + 1, data, index, numMoved);
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

    //додаємо елемент у вказане місце, переміщачи всі елементи на один в право
    @Override
    public <T extends E> void add(int index, T element) {
        rangeCheck(index);
        capacityCheck();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
    }

    //перевіряємо вмістимість масива, якщо достигли максимуму збільшуємо в 2-ва ризи
    private void capacityCheck() {
        if (GROW_CAPACITY <= size + 1) {
            GROW_CAPACITY = GROW_CAPACITY + (GROW_CAPACITY >> 1);
            if (GROW_CAPACITY < 0) {
                throw new OutOfMemoryError();
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
}