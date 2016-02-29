package edu.BIV123.SieveEratosthenes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneThreadRealization implements Algorithm {
    @Override
    public List<Integer> findSimpleNumbers(int n) {
        List<Integer> result = new ArrayList<>();

        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, 2, n + 1, true);

        for (int i = 2; i * i <= n; i++) {
            if (primes[i]) {
                for (int k = i * i; k <= n; k += i) {
                    primes[k] = false;
                }
            }
        }

        for (int i = 0; i < primes.length; i++) {
            if (primes[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
