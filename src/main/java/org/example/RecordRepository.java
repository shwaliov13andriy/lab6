package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecordRepository<K, V extends Identifiable<K>> implements Iterable<V> {

    private final Map<K, V> storage = new LinkedHashMap<>();

    public void save(V record) {
        if (record == null || record.getKey() == null) {
            throw new IllegalArgumentException("Запис та його ключ не можуть бути null");
        }
        storage.put(record.getKey(), record);
    }

    public V findByKey(K key) {
        return storage.get(key);
    }

    public int size() {
        return storage.size();
    }

    public List<V> getSorted(Comparator<? super V> comparator) {
        List<V> result = new ArrayList<>(storage.values());
        Collections.sort(result, comparator);
        return Collections.unmodifiableList(result);
    }

    public Collection<V> getAll() {
        return Collections.unmodifiableCollection(storage.values());
    }

    @Override
    public Iterator<V> iterator() {
        return Collections.unmodifiableCollection(storage.values()).iterator();
    }
}