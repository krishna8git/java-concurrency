package org.vit.collections.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapLRUCache<K, V> implements ILRUCache<K, V> {

    private final int capacity;
    private final Map<K, V> cache;

    public LinkedHashMapLRUCache(final int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void add(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public String toString() {
        return cache.toString();
    }
}
