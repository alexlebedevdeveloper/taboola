package tasks.questionthree.test.solutions;

import java.util.ArrayList;
import java.util.List;

public abstract class StringsTransformerAbstractImpl {
    protected static final int THREAD_NUMBER = 10;

    protected List<String> data;

    public List<String> getData() {
        return data;
    }

    public StringsTransformerAbstractImpl(List<String> startingData){
        this.data = startingData;
    }

    public void forEach(StringFunction function){
        List<String> newData = new ArrayList<String>();
        for (String str : data) {
            newData.add(function.transform(str));
        }
        this.data = newData;
    }

    public abstract List<String> transform(final List<StringFunction> functions) throws InterruptedException;

    public static interface StringFunction {
        String transform(String str);
    }
}
