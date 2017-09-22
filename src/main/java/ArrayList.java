import java.util.Arrays;
import java.util.NoSuchElementException;

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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public java.util.Iterator iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator {
        int cursor = 0;
        int lastReturn = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if(cursor >= size){
                throw new NoSuchElementException();
            }
            lastReturn = cursor;
            cursor++;
            return ArrayList.this.get(lastReturn);
        }

        @Override
        public void remove() {
            if(lastReturn<0) throw new IllegalStateException();

            ArrayList.this.remove(lastReturn);

            cursor = lastReturn;
            lastReturn--;
        }
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
                if (element.equals(data[0])) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        return (E[]) Arrays.copyOf(data, size);
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("get index =" + index + "but array size = " + size);
        }
    }

    @Override
    public <T extends E> E set(int index, T element) {
        rangeCheck(index);
        E oldObject = data[index];
        data[index] = element;
        return oldObject;
    }

    @Override
    public <T extends E> boolean add(T element) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

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

    @Override
    public <T extends E> void add(int index, T element) {
        rangeCheck(index);
        capacityCheck();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

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