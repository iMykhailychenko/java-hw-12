package task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.stream.Stream;


public class FizzBuzz {
    private int amount;
    private String[] results;

    public void run(int n) {
        amount = n;
        results = new String[n];

        Collection<Callable<Void>> tasks = Arrays.asList(
                this::fizz,
                this::buzz,
                this::fizzbuzz,
                this::number
        );

        ExecutorService threads = Executors.newFixedThreadPool(4);
        try {
            threads.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (String result : results) {
            System.out.println(result);
        }

        threads.shutdown();
    }

    private Stream<Integer> getStream() {
        return Stream.iterate(1, x -> x + 1).limit(amount);
    }

    private Void fizz() {
        getStream().forEach(n -> {
            if (n % 3 == 0 && n % 5 != 0) {
                results[n - 1] = "FIZZ";
            }
        });

        return null;
    }

    private Void buzz() {
        getStream().forEach(n -> {
            if (n % 3 != 0 && n % 5 == 0) {
                results[n - 1] = "BUZZ";
            }
        });

        return null;
    }

    private Void fizzbuzz() {
        getStream().forEach(n -> {
            if (n % 5 == 0 && n % 3 == 0) {
                results[n - 1] = "FIZZBUZZ";
            }
        });

        return null;
    }

    private Void number() {
        getStream().forEach(n -> {
            if (n % 5 != 0 && n % 3 != 0) {
                results[n - 1] = String.valueOf(n);
            }
        });

        return null;
    }
}
