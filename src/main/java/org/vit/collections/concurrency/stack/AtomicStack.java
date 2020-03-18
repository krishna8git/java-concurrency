package org.vit.collections.concurrency.stack;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A thread safe stack which non blocking.
 * It ensures thread safety by using the CAS operation on
 * the "top" reference.
 * <p>
 * Reference: https://www.ibm.com/developerworks/library/j-jtp04186/index.html
 */
public class AtomicStack<E> implements Stack<E> {

    private final AtomicReference<Node<E>> top = new AtomicReference<>();

    /**
     * Atomic Push Operation
     */
    @Override
    public void push(E item) {
        Node<E> newTop = new Node<>(item);
        Node<E> oldTop;
        do {
            oldTop = top.get();
            newTop.next = oldTop;
        } while (!top.compareAndSet(oldTop, newTop));
    }

    /**
     * Atomic Pop Operations
     * @return: the top item atomically.
     */
    @Override
    public E pop() {
        Node<E> oldTop;
        Node<E> newTop;
        do {
            oldTop = top.get();
            if (oldTop == null) {
                return null;
            }
            newTop = oldTop.next;
        } while (!top.compareAndSet(oldTop, newTop));
        return oldTop.item;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    private class Node<Item> {
        private Item item;
        private Node<Item> next;

        private Node(Item item) {
            this.item = item;
        }
    }

}
