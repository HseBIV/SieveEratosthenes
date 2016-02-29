package edu.BIV123.SieveEratosthenes;

import edu.BIV123.SieveEratosthenes.algorithms.Algorithm;
import edu.BIV123.SieveEratosthenes.algorithms.OneThreadRealization;
import edu.BIV123.SieveEratosthenes.algorithms.MultithreadsRealization;

public class Solution {
    public static void main(String[] args) {
        ConsoleHelper.writeMessage("The algorithm sieve of Eratosthenes");
        int n = ConsoleHelper.askNumberN();

        Algorithm algorithm;

        ConsoleHelper.writeMessage("Multithread realization ");
        //ConsoleHelper.writeMessage("Simple numbers:");

        algorithm = new MultithreadsRealization();

        long start = System.currentTimeMillis();
        algorithm.findSimpleNumbers(n);
        long stop = System.currentTimeMillis();

        //ConsoleHelper.writeNumbers(numbers);
        System.gc();

        long multithreadsMillis = stop - start;
        ConsoleHelper.writeMessage("Time: " + multithreadsMillis + " ms");


        ConsoleHelper.writeMessage("One thread realization");
        //ConsoleHelper.writeMessage("Simple numbers:");

        algorithm = new OneThreadRealization();

        start = System.currentTimeMillis();
        algorithm.findSimpleNumbers(n);
        stop = System.currentTimeMillis();

        //ConsoleHelper.writeNumbers(numbers);
        System.gc();

        long oneThreadMillis = stop - start;
        ConsoleHelper.writeMessage("Time: " + oneThreadMillis + " ms");


        if (multithreadsMillis < oneThreadMillis) {
            ConsoleHelper.writeMessage("Multithread realization faster than one thread realization on " +
                    (oneThreadMillis - multithreadsMillis));
        } else {
            ConsoleHelper.writeMessage("One thread realization faster than multithread realization on " +
                    (multithreadsMillis - oneThreadMillis));
        }
    }
}

