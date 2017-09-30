public class ArrayListTest extends AbstractList {

    @Override
    protected List<String> getStringList() {
        return new ArrayList<>();
    }

    @Override
    protected List<Integer> getIntegerList() {
        return new ArrayList<>();
    }

    @Override
    protected List<Object> getEmptyList() {
        return new ArrayList<>();
    }
}