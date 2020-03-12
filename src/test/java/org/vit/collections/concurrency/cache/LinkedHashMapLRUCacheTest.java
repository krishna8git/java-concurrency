package org.vit.collections.concurrency.cache;

import org.junit.Test;
import org.vit.collections.cache.LinkedHashMapLRUCache;

public class LinkedHashMapLRUCacheTest {

    @Test
    public void testCache() {
        LinkedHashMapLRUCache<Integer, Integer> cache = new LinkedHashMapLRUCache<>(5);
        cache.add(1, 1);
        cache.add(2, 2);
        cache.add(3, 3);
        cache.add(4, 4);
        cache.add(5, 5);
        System.out.println(cache);

        cache.get(4);
        System.out.println(cache);
    }
}
