import java.util.Iterator;

public interface Collection<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    boolean add(E e);

    boolean remove(Object o);

    boolean addAll(java.util.Collection<? extends E> c);

    boolean removeAll(java.util.Collection<?> c);

    boolean retainAll(java.util.Collection<?> c);

    void clear();

    boolean equals(Object o);

    int hashCode();
}