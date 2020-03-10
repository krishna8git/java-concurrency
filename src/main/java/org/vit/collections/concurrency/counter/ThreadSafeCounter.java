package org.vit.collections.concurrency.counter;

public interface ThreadSafeCounter {

    /**
     * Atomically increments the given value.
     *
     * @return the previous value
     */
    public int getAndIncrement();

    /**
     * Atomically increments the given value.
     *
     * @return the incremented value
     */
    public int incrementAndGet();

    /**
     * Atomically decrements the given value.
     *
     * @return the previous value.
     */
    public int getAndDecrement();

    /**
     * Atomically decrements the given value.
     *
     * @return the decremented value.
     */
    public int decrementAndGet();

    public int get();

    /**
     * Resets the counter to zero
     */
    public void reset() throws UnsupportedOperationException;

    /**
     * resets the counter to the given value.
     *
     * @param resetVal - The value which counter should be reset to.
     */
    public void reset(int resetVal) throws UnsupportedOperationException;
}
