package tasks.questionthree.test.solutions;

public class StringFunctionPlus implements StringsTransformerAbstractImpl.StringFunction {
    @Override
    public String transform(String str) {
        return str + " PLUS ";
    }
}
