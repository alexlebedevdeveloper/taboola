package tasks.questionthree.test.solutions;

import java.util.*;

public class StringsTransformer extends StringsTransformerAbstractImpl {

    public StringsTransformer(List<String> startingData) {
        super(startingData);
    }

    // Issue - main problem here is not a thread safe code.
    // Code we run is run simultaneously cause we run all threads at the same time.
    // Switch context is more than expected here

    // Addition low-level issues :
    // 1. We can not control here execution order
    // 2. Due to the problem from the main issue - we can lose transformation data

    @Override
    public List<String> transform(List<StringFunction> functions) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (final StringFunction f : functions) {
            threads.add(new Thread(() -> forEach(f)));
        }
        for (Thread t : threads) {
            t.join();
        }
        return data;
    }
}
