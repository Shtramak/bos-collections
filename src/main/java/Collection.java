public interface Collection<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    <T extends E> boolean contains(T element);

    E[] toArray();

    <T extends E> boolean add(T element);

    <T extends E> boolean remove(T element);

    <T extends E> void add(int index, T element);

    void clear();
}