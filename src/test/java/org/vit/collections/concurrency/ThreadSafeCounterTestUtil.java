package org.vit.collections.concurrency;

import org.junit.Assert;
import org.junit.Test;
import org.vit.collections.concurrency.counter.ThreadSafeCounter;
import org.vit.collections.utils.Utils;

import java.util.concurrent.*;

public class ThreadSafeCounterTestUtil {

    private final ThreadSafeCounter counter;

    public ThreadSafeCounterTestUtil(ThreadSafeCounter counter) {
        this.counter = counter;
    }

    @Test
    public void testGetAndIncrement() {
        counter.reset();
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            counter.getAndIncrement();
        }
        long end = System.nanoTime();
        Assert.assertEquals(counter.get(), 1000000);
        System.out.println("Counter Value: " + counter.get());
        System.out.println("Time Taken to 1000000(1Million) increments: " + Utils.getElapsedMillisFromNanos(start, end) / 1000.0 + " seconds");
    }

    /**
     * This test performs hundred iterations.
     * For Each iterations, it has two threads:
     * One increments one Million times
     * And the other decrements half millions times.
     * And it asserts for half million, as our counter is thread safe it should give
     * (1 Million increments - half Million decrements) = Half Million
     * Because in thread safe environment, it should be consistent and no race conditions should happen.
     * If any race condition happens, that value will not be equal to half million.
     */
    @Test
    public void testCounterThreadSafety() throws ExecutionException, InterruptedException {
        for (int i = 1; i <= 100; i++) {
            counter.reset();
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            long start = System.nanoTime();
            Future<Integer> incrementorResult = executorService.submit(() -> increment1Million(counter));
            Future<Integer> decrementorResult = executorService.submit(() -> decrementHalfMillion(counter));
            while (executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                // Nothing to do but busy wait
            }
            long end = System.nanoTime();

            System.out.println("Iteration # " + i + ". After 1500000 transactions: " + counter.get() +
                    ". Time taken for two threads to be completed: " +
                    String.format("%f seconds.", Utils.getElapsedMillisFromNanos(start, end) / 1000.0));
            Assert.assertEquals(500000, counter.get());
            // System.out.println(String.format("Incrementor Result: %d, Decrementor Result: %d",
            // incrementorResult.get(), decrementorResult.get()));
        }
    }

    private int increment1Million(ThreadSafeCounter counter) {
        for (int i = 0; i < 1000000; i++) {
            counter.incrementAndGet();
        }
        return counter.get();
    }

    private int decrementHalfMillion(ThreadSafeCounter counter) {
        for (int i = 0; i < 500000; i++) {
            counter.decrementAndGet();
        }
        return counter.get();
    }
}
