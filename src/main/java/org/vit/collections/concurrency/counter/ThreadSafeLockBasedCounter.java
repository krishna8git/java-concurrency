package org.vit.collections.concurrency.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread Safe Counter Which is lock based.
 */
public class ThreadSafeLockBasedCounter implements ThreadSafeCounter {

    /**
     * counter variable
     */
    private int count;

    /**
     * Lock to ensure atomicity
     */
    private final Lock lock = new ReentrantLock();

    /**
     * Atomically increments the given value.
     *
     * @return the previous value
     */
    @Override
    public int getAndIncrement() {
        try {
            lock.lock();
            int oldVal = count;
            count = count + 1;
            return oldVal;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Atomically increments the given value.
     *
     * @return the incremented value
     */
    @Override
    public int incrementAndGet() {
        lock.lock();
        try {
            count = count + 1;
            return count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Atomically decrements the given value.
     *
     * @return the previous value.
     */
    @Override
    public int getAndDecrement() {
        lock.lock();
        try {
            int oldVal = count;
            count = count - 1;
            return oldVal;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Atomically decrements the given value.
     *
     * @return the decremented value.
     */
    @Override
    public int decrementAndGet() {
        lock.lock();
        try {
            count = count - 1;
            return count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * gives the recent value.
     *
     * @return the latest value.
     */
    @Override
    public int get() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Resets the counter to zero.
     */
    @Override
    public void reset() {
        lock.lock();
        try {
            reset(0);
        } finally {
            lock.unlock();
        }
    }

    /**
     * resets the counter to the given value.
     *
     * @param resetVal - The value which counter should be reset to.
     */
    public void reset(int resetVal) {
        lock.lock();
        try {
            count = resetVal;
        } finally {
            lock.unlock();
        }
    }
}
