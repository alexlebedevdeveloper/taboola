package tasks.questionthree.test.solutions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tasks.questionthree.test.solutions.alternatives.StringsTransformerAlternativeAwait;
import tasks.questionthree.test.solutions.alternatives.StringsTransformerAlternativeThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StringsTransformerTest {

    private List<String> listOFStrings;
    List<StringsTransformerAbstractImpl.StringFunction> listOfFunctions;
    private static final String DATA_TEST = "Test ";

    @Before
    public void setup(){
        listOFStrings = new ArrayList<>();
        IntStream.range(0,50)
                .forEachOrdered(index-> listOFStrings.add(DATA_TEST + index));
        listOfFunctions = new ArrayList<>();
        StringFunctionMinus minusFunction = new StringFunctionMinus();
        StringFunctionPlus plusFunction = new StringFunctionPlus();
        listOfFunctions.add(minusFunction);
        listOfFunctions.add(plusFunction);
    }

    @Test
    public void transformByTaboolaDefault() throws InterruptedException {
        StringsTransformer stringsTransformer = new StringsTransformer(listOFStrings);
        List<String> transformedData = stringsTransformer.transform(listOfFunctions);
        System.out.println(transformedData);
        assertEquals(transformedData.size(), listOFStrings.size());
    }

    @Test
    public void transformThreadPoolWay() throws InterruptedException {
        StringsTransformerAlternativeThreadPool stringsTransformer = new StringsTransformerAlternativeThreadPool(listOFStrings);
        List<String> transformedData = stringsTransformer.transform(listOfFunctions);
        System.out.println(transformedData);
        assertEquals(transformedData.size(), listOFStrings.size());
    }

    @Test
    public void transformThreadPoolWayWithAwait() throws InterruptedException {
        StringsTransformerAlternativeAwait stringsTransformer = new StringsTransformerAlternativeAwait(listOFStrings);
        List<String> transformedData = stringsTransformer.transform(listOfFunctions);
        System.out.println(transformedData);
        assertEquals(transformedData.size(), listOFStrings.size());
    }
}
