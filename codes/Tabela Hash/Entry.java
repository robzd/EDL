public interface Entry {
    public Object key();

    public Object value();
}

class Item implements Entry {
    private Object k, v;

    public Item(Object key, Object value) {
        k = key;
        v = value;
    }

    public Object key() {
        return k;
    }

    public Object value() {
        return v;
    }

    public void setValue(Object value) {
        v = value;
    }
}
