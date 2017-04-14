package gas.home.pricewatcher.util;

public class GenericPair<K, V> {

    private K key;
    private V value;

    public GenericPair() {
    }

    public GenericPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public GenericPair(GenericPair genericPair) {
        genericPair.key = key;
        genericPair.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericPair<?, ?> genericPair = (GenericPair<?, ?>) o;

        if (key != null ? !key.equals(genericPair.key) : genericPair.key != null) return false;
        return value != null ? value.equals(genericPair.value) : genericPair.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
