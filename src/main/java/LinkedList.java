import java.util.Iterator;

public class LinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    private static class Node<T> {
        private Node<T> prev;
        private T item;
        private Node<T> next;

        private Node(Node<T> prev, T item, Node<T> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
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
    public <T extends E> boolean contains(T element) {
        return false;
    }

    @Override
    public E[] toArray() {
        return (E[]) new Object[0];
    }

    @Override
    public <T extends E> boolean add(T element) {
        return false;
    }

    @Override
    public <T extends E> boolean remove(T element) {
        return false;
    }

    @Override
    public <T extends E> void add(int index, T element) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
    public E remove(int index) {
        return null;
    }

    @Override
    public <T extends E> int indexOf(T element) {
        return 0;
    }
}
