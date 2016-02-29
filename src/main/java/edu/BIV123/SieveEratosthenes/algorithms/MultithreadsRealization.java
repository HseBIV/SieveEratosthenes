package edu.BIV123.SieveEratosthenes.algorithms;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultithreadsRealization implements Algorithm {
    private Semaphore semaphore = new Semaphore(0);
    private static final int THREADS = 2;
    private ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    private HashSet<Integer> result = new LinkedHashSet<>();
    private boolean[] primes;
    private ReadWriteLock rwPrimes = new ReentrantReadWriteLock();

    private synchronized void setSimpleNumber(int num) {
        result.add(num);
    }

    private boolean getPrimeStatus(int i) {
        try {
            rwPrimes.readLock().lock();
            return primes[i];
        } finally {
            rwPrimes.readLock().unlock();
        }
    }

    private void setPrimeStatus(int i, boolean status) {
        try {
            rwPrimes.writeLock().lock();
            primes[i] = status;
        } finally {
            rwPrimes.writeLock().unlock();
        }
    }

    private class Worker implements Runnable {
        private final boolean[] primes;
        private final int numFrom;

        public Worker(boolean[] primes, int numFrom) {
            this.primes = primes;
            this.numFrom = numFrom;
        }

        @Override
        public void run() {
            int i = numFrom;
            for (; i * i < primes.length; i += 2) {
                if (getPrimeStatus(i)) {
                    setSimpleNumber(i);
                    for (int j = i * i; j < primes.length; j += i) {
                        setPrimeStatus(j, false);
                    }
                }
            }

            for (; i < primes.length; i++) {
                if (getPrimeStatus(i)) {
                    setSimpleNumber(i);
                }
            }

            semaphore.release();
        }
    }

    @Override
    public List<Integer> findSimpleNumbers(int n) {
        result.clear();

        int length = n + 1;
        primes = new boolean[length];
        Arrays.fill(primes, 2, n + 1, true);

        executor.submit(new Worker(primes, 2));
        executor.submit(new Worker(primes, 3));

        try {
            semaphore.acquire(THREADS);
        } catch (InterruptedException e) {

        }

        executor.shutdown();

        return new ArrayList<>(result);
    }
}
