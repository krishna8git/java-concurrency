package org.vit.collections.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements LRU Cache. This class is not thread-safe which means it does not guarantee
 * the access order in a multi-threaded environment.
 * LRU cache is the famous algorithm that is used in page replacement techniques.
 * This class maintains map of key value pairs and a doubly linked list to accommodate the access order.
 * As the entries are getting accessed, the recently accessed entry will be brought to the front of the queue.
 * And the least recently used value will be pushed to the end of the queue.
 *
 * The Thread-Safe version of this class can be found in the package: org.vit.collections.concurrency.cache
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {

    private class Node<Key, Value> {
        private Node<Key, Value> prev;
        private Key key;
        private Value value;
        private Node<Key, Value> next;

        private Node(Node<Key, Value> prev, Key key, Value value, Node<Key, Value> next) {
            this.prev = prev;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node)) {
                return false;
            }
            Node<Key, Value> node = (Node<Key, Value>) other;
            return key.equals(node.key) &&
                    value.equals(node.value) &&
                    prev == node.prev &&
                    next == node.next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private final Map<K, Node<K, V>> cache;
    private final int capacity;
    private int size;
    private Node<K, V> head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        size = 0;
    }

    /**
     * Adds the entry to the LRU Cache.
     *
     * @param key:   Key of the Cache Entry
     * @param value: Value of the Cache Entry
     */
    public void add(K key, V value) {
        Node<K, V> node = cache.get(key);

        // If there is already a node available
        // Update the value and bring it to front.
        if (node != null) {
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node newNode = new Node(null, key, value, null);
            cache.put(key, newNode);
            if (size < capacity) {
                size++;
                addToHead(newNode);
            } else {
                cache.remove(tail.key);
                deleteAndUpdateTail();
                addToHead(newNode);
            }
        }
    }

    /**
     * Get the key from the cache and preservs the access order which means it
     * brings up the recently used value to the front of the cache.
     * @param key
     * @return value associated with the given key. If there is no key it returns null.
     */
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        V result = node.value;
        if (!(node == head) && !node.equals(head)) {
            deleteNode(node);
            addToHead(node);
        }
        return result;
    }

    /**
     * Deletes the old tail.
     * The new tail is the previous node of the old tail.
     */
    private void deleteAndUpdateTail() {
        tail = tail.prev;
        tail.next.prev = null;
        tail.next = null;
    }

    /**
     * A handy tool that removes the node.
     *
     * @param node
     */
    private void deleteNode(Node<K, V> node) {
        if (node == tail || node.equals(tail)) {
            deleteAndUpdateTail();
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * A handy method that adds a node to the head of the Cache.
     */
    private void addToHead(Node<K, V> node) {
        if (head == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head.prev = node;
        node.prev = null;
        head = node;
    }

    /**
     * This method is just for debugging purpose. The method is not meant to maintain
     * access order of the cache. This is just to support unit testing and not meant to be
     * in the collection and not to be used by the client to get the element from the cache.
     * <p>
     * NOTE: Please look at the unit test to know the purpose of this method.
     */
    public V getFirst() {
        return head.value;
    }

    /**
     * This method is just for debugging purpose. The method is not meant to maintain
     * access order of the cache. This is just to support unit testing and not meant to be
     * in the collection and not to be used by the client to get the element from the cache.
     * <p>
     * NOTE: Please look at the unit test to know the purpose of this method.
     */
    public V getLast() {
        return tail.value;
    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "cache=" + cache +
                '}';
    }
}
