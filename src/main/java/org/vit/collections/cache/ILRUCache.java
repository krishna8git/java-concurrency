package org.vit.collections.cache;

public interface ILRUCache<K, V> {

    void add(K key, V value);

    V get(K key);

}
