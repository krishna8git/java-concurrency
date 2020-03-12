package org.vit.collections.concurrency.cache;

import org.junit.Assert;
import org.junit.Test;
import org.vit.collections.cache.LRUCache;

import java.util.Random;

public class LRUCacheTest {

    LRUCache<Integer, Integer> lruCache = new LRUCache<>(5);

    @Test
    public void testLRUCache() {
        // add the elements in to the lru cache
        lruCache.add(1, 1);
        lruCache.add(2, 2);
        lruCache.add(3, 3);
        lruCache.add(4, 4);
        lruCache.add(5, 5);

        // get the first element this should be 5. Because that is most recently added.
        System.out.println("Least Recently Used After adding 5 Elements: " + lruCache.getFirst());
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(Integer.valueOf(5), lruCache.getFirst());

        // We are getting the value 3 from the cache. And that should brought first.
        Integer lruVal = lruCache.get(3);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruCache.add(6, 6);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(Integer.valueOf(6), lruCache.getFirst());

        lruVal = lruCache.get(3);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruVal = lruCache.get(2);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruVal = lruCache.get(4);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruVal = lruCache.get(4);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruVal = lruCache.get(2);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruVal = lruCache.get(2);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        lruVal = lruCache.get(4);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());

        new Random().ints(100, 2, 6).map(Integer::valueOf).forEach(this::assertLRU);
    }

    private void assertLRU(Integer i) {
        Integer lruVal = lruCache.get(i);
        System.out.println("Most Recent: " + lruCache.getFirst() + " Least Recent: " + lruCache.getLast());
        Assert.assertEquals(lruVal, lruCache.getFirst());
    }

}
