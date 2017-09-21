import java.util.Iterator;

public interface List<E> extends Collection<E> {

    Iterator<E> iterator();

    E get(int index);

    <T extends E> E set(int index, T element);

    E remove(int index);

    <T extends E> int indexOf(T element);
}
