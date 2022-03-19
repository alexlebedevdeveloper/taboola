package tasks.questionthree.test.solutions.alternatives;

import tasks.questionthree.test.solutions.StringsTransformerAbstractImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StringsTransformerAlternativeThreadPool extends StringsTransformerAbstractImpl {

    public StringsTransformerAlternativeThreadPool(List<String> startingData) {
        super(startingData);
    }

    // This approach is using thread pool and iterating over strings firstly.
    // Main advantage is that we are running and applying transformations for each string.
    // It will not interfere with other strings and switch context will not happen.
    // We are handling strings separately.

    @Override
    public List<String> transform(final List<StringFunction> functions) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
        List<List<String>> tempList = data.stream().map(stringValue -> transformExecution(functions, executor, stringValue)).collect(Collectors.toList());
        executor.awaitTermination(2, TimeUnit.SECONDS);
        executor.shutdown();
        List<String> finalList = new ArrayList<>();
        tempList.forEach(data -> finalList.add(data.get(data.size()-1)));
        return finalList;
    }

    private List<String> transformExecution(List<StringFunction> functions, ExecutorService executor, String stringValue) {
        List<String> listOfFinals = new ArrayList<>();
        listOfFinals.add(stringValue);
        executor.execute(() -> {
            for (StringFunction stringFunction : functions) {
                listOfFinals.add(stringFunction.transform(listOfFinals.get(listOfFinals.size()-1)));
            }
        });
        return listOfFinals;
    }
} 
