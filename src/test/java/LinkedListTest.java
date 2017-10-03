public class LinkedListTest extends AbstractList {

    @Override
    protected List<String> getStringList() {
        return new LinkedList<>();
    }

    @Override
    protected List<Integer> getIntegerList() {
        return new LinkedList<>();
    }

    @Override
    protected List<Object> getEmptyList() {
        return new LinkedList<>();
    }
}
