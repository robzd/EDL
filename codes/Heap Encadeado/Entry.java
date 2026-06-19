interface Entry {
    Object key();

    Object value();
}

class MyEntry implements Entry {
    private Object k, v;

    public MyEntry(Object key, Object value) {
        k = key;
        v = value;
    }

    public Object key() {
        return k;
    }

    public Object value() {
        return v;
    }

    public void setKey(Object k) {
        this.k = k;
    }

    public void setValue(Object v) {
        this.v = v;
    }
}