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

    //Згідний з такою перевіркою на розмірність   метод capacityCheck() або лишній або привести до вигляду як у методі add()
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

    //Вилучення елементу по індексу

    //Є окремий метод rangeCheck(index) перевірка індексу в ньому.
    // Лишив свою реалізацію, окрім перевірки (numMoved > 0)
    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
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
        int endIndex = result.length()-1;
        result.replace(startIndex,endIndex,"]");
        return result.toString().trim();
    }
}