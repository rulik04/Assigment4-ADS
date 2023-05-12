public class KeyAndValue<K, V> {
    private V value;
    private K key;

    public KeyAndValue() {}

    public KeyAndValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "[" + key + ", " + value + "]";
    }
}