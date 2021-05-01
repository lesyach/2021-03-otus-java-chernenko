package homework;

import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private static TreeMap<Customer, String> treeMap = new TreeMap<Customer, String>();
    private final class MapEntry<K extends Customer, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public K getKey() {
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }
        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallestEntry = null;
        for(Map.Entry<Customer, String> entry : treeMap.entrySet()) {
            if(smallestEntry == null
                    || smallestEntry.getKey().getScores()>entry.getKey().getScores()) {
                smallestEntry = new MapEntry<>(
                        new Customer(entry.getKey().getId(),
                                entry.getKey().getName(),
                                entry.getKey().getScores()), entry.getValue());
            }
        }
        return smallestEntry;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = treeMap.higherEntry(customer);
        return entry!=null ? new MapEntry<>(
                new Customer(entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getKey().getScores()), entry.getValue()) : null;
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }
}
