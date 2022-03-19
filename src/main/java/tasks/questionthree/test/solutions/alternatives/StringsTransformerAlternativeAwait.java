package tasks.questionthree.test.solutions.alternatives;

import tasks.questionthree.test.solutions.StringsTransformerAbstractImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StringsTransformerAlternativeAwait extends StringsTransformerAbstractImpl {

    public StringsTransformerAlternativeAwait(List<String> startingData) {
        super(startingData);
    }

    // This solution is about usage of CountDownLatch on top of thread pool and iterating over functions.
    // In this alternative we are running on functions and not on strings firstly.
    // Disadvantage on regular thread pool implementation is :
    // Next function will not start running until all tasks on previous function will not end.

    @Override
    public List<String> transform(final List<StringFunction> functions) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
        functionExecutions(functions, executor);
        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();
        return data;
    }

    private void functionExecutions(List<StringFunction> functions, ExecutorService executor) {
        functions.forEach(function -> {
            List<String> tempList = new ArrayList<>();
            final CountDownLatch countDownLatch = new CountDownLatch(data.size());
            for (String str : data) {
                executor.execute(() -> {
                    tempList.add(function.transform(str));
                    countDownLatch.countDown();
                });
            }

            try {
                countDownLatch.await();
                data = tempList;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

} 