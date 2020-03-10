package org.vit.collections.concurrency;

import org.junit.Before;
import org.junit.Test;
import org.vit.collections.concurrency.counter.ThreadSafeLockBasedCounter;

import java.util.concurrent.ExecutionException;

public class ThreadSafeLockBasedCounterTest {

    private ThreadSafeCounterTestUtil counterUtil;

    @Before
    public void init() {
        counterUtil = new ThreadSafeCounterTestUtil(new ThreadSafeLockBasedCounter());
    }

    @Test
    public void testGetAndIncrement() {
        counterUtil.testGetAndIncrement();
    }

    @Test
    public void testCounterThreadSafety() throws ExecutionException, InterruptedException {
        counterUtil.testCounterThreadSafety();
    }

}
