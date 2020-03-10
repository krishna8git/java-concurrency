package org.vit.collections.concurrency;

import org.junit.Before;
import org.junit.Test;
import org.vit.collections.concurrency.counter.ThreadSafeCASBasedCounter;

import java.util.concurrent.*;

public class ThreadSafeCASBasedCounterTest {

    private ThreadSafeCounterTestUtil counterUtil;

    @Before
    public void init() {
        counterUtil = new ThreadSafeCounterTestUtil(new ThreadSafeCASBasedCounter());
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
