package tasks.questionthree.test.solutions;

public class StringFunctionMinus implements StringsTransformerAbstractImpl.StringFunction {
    @Override
    public String transform(String str) {
        return str + " MINUS ";
    }
}
