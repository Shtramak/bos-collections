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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return (size - index > 0);
            }

            @Override
            public E next() {
                return data[index++];
            }
        };
    }

    @Override
    public <T extends E> boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public <T extends E> boolean remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (data[i].equals(element)) {
                remove(i);
                return true;
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

    @Override
    public E get(int index) {
        return null;
    }

    //Перевірка не вийшов індекс за межі массиву
    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("get index =" + index + "but array size = " + size);
        }
    }

    //Заміняємо елемент в указаному місці
    @Override
    public <T extends E> E set(int index, T element) {
        rangeCheck(index);
        E oldObject = data[index];
        data[index] = element;
        return oldObject;
    }

    @Override
    public <T extends E> boolean add(T element) {
        if (data.length == size) {
            E[] tmp = (E[]) new Object[size * 2];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
        data[size++] = element;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("Argument is out from range");

        E removedElement = (E) data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(data, index+1, data, index, numMoved);
        data[--size] = null;

        return removedElement;
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
        size++;
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

    @Override
    public void clear() {

    }
}