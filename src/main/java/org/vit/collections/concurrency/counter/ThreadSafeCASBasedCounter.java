package org.vit.collections.concurrency.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread Safe Counter Which is CAS(Compare and Swap) based.
 */
public class ThreadSafeCASBasedCounter implements ThreadSafeCounter {

    /**
     * counter variable
     */
    private final AtomicInteger counter = new AtomicInteger();

    /**
     * Atomically increments the given value.
     *
     * @return the previous value
     */
    @Override
    public int getAndIncrement() {
        return counter.getAndIncrement();
    }

    /**
     * Atomically increments the given value.
     *
     * @return the incremented value
     */
    @Override
    public int incrementAndGet() {
        return counter.incrementAndGet();
    }

    /**
     * Atomically decrements the given value.
     *
     * @return the previous value.
     */
    @Override
    public int getAndDecrement() {
        return counter.getAndDecrement();
    }

    /**
     * Atomically decrements the given value.
     *
     * @return the decremented value.
     */
    @Override
    public int decrementAndGet() {
        return counter.decrementAndGet();
    }

    /**
     * gives the recent value.
     *
     * @return the latest value.
     */
    @Override
    public int get() {
        return counter.get();
    }

    /**
     * Resets the counter to zero.
     */
    @Override
    public void reset() {
        reset(0);
    }

    /**
     * resets the counter to the given value.
     *
     * @param resetVal - The value which counter should be reset to.
     */
    @Override
    public void reset(int resetVal) {
        counter.getAndSet(resetVal);
    }
}
