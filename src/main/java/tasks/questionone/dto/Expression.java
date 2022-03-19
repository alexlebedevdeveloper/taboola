package tasks.questionone.dto;

import tasks.questionone.common.Action;

import java.util.HashMap;

public class Expression extends Action {
    private final Action leftSide;
    private final Operators operator;
    private final Action right;

    public Expression(Action leftSide, Operators operator, Action rightSide) {
        this.leftSide = leftSide;
        this.right = rightSide;
        this.operator = operator;
    }

    @Override
    public int evaluateAction(HashMap<Character, Integer> postIncrements) {
        int previousLeftValue = leftSide.evaluateAction(postIncrements);
        int addedValue = right.evaluateAction(postIncrements);

        switch (operator) {
            case ADD:
                return previousLeftValue + addedValue;
            case SUBTRACT:
                return previousLeftValue - addedValue;
            case MULTIPLY:
                return previousLeftValue * addedValue;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
