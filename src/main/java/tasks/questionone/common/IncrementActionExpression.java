package tasks.questionone.common;

import java.util.HashMap;

public class IncrementActionExpression extends Action {
    private final char variableOperation;
    private final HashMap<Character, Integer> mapOfReadyVariables;
    private final boolean isIncrement;

    IncrementActionExpression(char variableOperation, boolean isIncrement, HashMap<Character, Integer> mapOfReadyVariables) {
        this.variableOperation = variableOperation;
        this.mapOfReadyVariables = mapOfReadyVariables;
        this.isIncrement = isIncrement;
    }

    @Override
    public int evaluateAction(HashMap<Character, Integer> postIncrements) {
        if (isIncrement) {
            if (!postIncrements.containsKey(variableOperation)) {
                postIncrements.put(variableOperation, 0);
            }
            postIncrements.put(variableOperation, postIncrements.get(variableOperation) + 1);
        }
        if (!mapOfReadyVariables.containsKey(variableOperation)) {
            throw new UnsupportedOperationException();
        }

        return mapOfReadyVariables.get(variableOperation);
    }
}