import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
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
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T extends E> boolean contains(T element) {
        return false;
    }

    @Override
    public <T extends E> boolean remove(T element) {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        return (E[]) new Object[]{};
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public <T extends E> E set(int index, T element) {
        return null;
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
        return 0;
    }

    @Override
    public <T extends E> void add(int index, T element) {

    }

    @Override
    public void clear() {

    }
}