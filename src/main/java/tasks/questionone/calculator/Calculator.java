package tasks.questionone.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tasks.questionone.common.ExpressionBuilder;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Calculator{

    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    private HashMap<Character, Integer> savedEntryVariables = new HashMap<>();

    public void calculate(String expressionEntry) {
        ExpressionBuilder expression = new ExpressionBuilder();
        HashMap<Character, Integer> postIncrements = new HashMap<>();
        HashMap<Character, Integer> calculatedSavedVariables = new HashMap<>(savedEntryVariables);

        int resultOfExpressionAction = expression.build(expressionEntry, calculatedSavedVariables).evaluateAction(postIncrements);
        calculatedSavedVariables.put(expression.getMainVariableOfExpression(), resultOfExpressionAction);

        postIncrements.keySet().forEach(key -> {
            int addValue = postIncrements.get(key);
            calculatedSavedVariables.put(key, calculatedSavedVariables.get(key) + addValue);
        });
        savedEntryVariables = calculatedSavedVariables;
    }

    public void printCalculatedResults() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        final int sizeOfVariables = savedEntryVariables.keySet().size();
        AtomicInteger counter = new AtomicInteger();
        variablesAppender(stringBuilder, sizeOfVariables, counter);
        stringBuilder.append("}");
        logger.info(stringBuilder.toString());
    }

    private void variablesAppender(StringBuilder stringBuilder, int sizeOfVariables, AtomicInteger counter) {
        savedEntryVariables.keySet().forEach(variable -> {
            int currentIndex = counter.get();
            stringBuilder.append(variable);
            stringBuilder.append(" = ");
            stringBuilder.append(savedEntryVariables.get(variable));
            if (currentIndex < sizeOfVariables - 1) {
                stringBuilder.append(", ");
            }
            counter.set(++currentIndex);
        });
    }

    public HashMap<Character, Integer> getSavedEntryVariables() {
        return savedEntryVariables;
    }

}