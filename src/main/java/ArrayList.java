import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<E> implements List  {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size = 0;

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
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
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (data[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(data[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(minCapacity, data.length + (data.length >> 1));
            E[] newData = (E[]) new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            this.data = newData;
        }
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity(size + 1);
        data[size++] = (E) o;
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void add(int index, Object element) {
        rangeCheck(index);

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1,
                size - index);
        data[index] = (E) element;
        size++;
    }

    private void rangeCheck(int index) {
        if (index < 0 || size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (data[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(data[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public Object remove(int index) {
        rangeCheck(index);

        E oldValue = data[index];
        int numMoved = size - index - 1;
        System.arraycopy(data, index + 1, data, index, numMoved);
        data[--size] = null;
        return oldValue;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(data, index+1, data, index,
                    numMoved);
        data[--size] = null;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            data[i] = null;

        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);

        return data[index];
    }

    @Override
    public E set(int index, Object element) {
        rangeCheck(index);

        E oldValue = data[index];
        data[index] = (E) element;
        return oldValue;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }


    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
