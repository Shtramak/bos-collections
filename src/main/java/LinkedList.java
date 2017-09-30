import java.util.Iterator;

//shtramak: add, remove(int), indexOf, add(int, T), clear
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

    private Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
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
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    @Override
    public <T extends E> boolean remove(T element) {
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <T extends E> void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");

        if (index == size) {
            Node<E> l = last;
            Node<E> newNode = new Node<>(l, element, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
        } else {
            Node<E> pred = node(index).prev;
            Node<E> newNode = new Node<>(pred, element, node(index));
            node(index).prev = newNode;
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            size++;
        }
    }

    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }


    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
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
        if (index < 0 || index > size || size == 0)
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");

        return unlink(node(index));
    }

    @Override
    public <T extends E> int indexOf(T element) {
        int index = 0;
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder result = new StringBuilder("[");
        for (Node<E> e = first; e != null; e = e.next) {
            result.append(e.item).append(", ");
        }
        int startIndex = result.lastIndexOf(", ");
        result.replace(startIndex, result.length(), "]");
        return result.toString();
    }
}
