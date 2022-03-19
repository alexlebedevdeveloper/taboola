package tasks.questionone.common;

import tasks.questionone.dto.Expression;
import tasks.questionone.dto.Operators;
import tasks.questionone.exceptions.InvalidStatementException;

import java.util.HashMap;
import java.util.Scanner;

import static tasks.questionone.common.PatternValidations.INCREMENT_EQUALS;

public class ExpressionBuilder {

    private static final String PLUS_STRING_VALUE = "+";
    private static final String MINUS_STRING_VALUE = "-";
    private static final String MULTIPLY_STRING_VALUE = "*";

    private char mainVariableOfExpression;
    private PatternValidations patternValidations = new PatternValidations();

    public char getMainVariableOfExpression() {
        return mainVariableOfExpression;
    }

    public Action build(String expression, HashMap<Character, Integer> mapOfSavedVariables) {
        if (patternValidations.isInvalid(expression)) throw new InvalidStatementException();

        Scanner scanner = new Scanner(expression);

        mainVariableOfExpression = scanner.next().charAt(0);
        if (!mapOfSavedVariables.containsKey(mainVariableOfExpression)) mapOfSavedVariables.put(mainVariableOfExpression, 0);

        if (scanner.next().equals(INCREMENT_EQUALS)) {
            Action leftAction = new IncrementActionExpression(mainVariableOfExpression, false, mapOfSavedVariables);
            return new Expression(leftAction, Operators.ADD, buildExpression(scanner, mapOfSavedVariables));
        }
        return buildExpression(scanner, mapOfSavedVariables);
    }

    private Action buildExpression(Scanner scanner, HashMap<Character, Integer> mapOfSavedVariables) {
        Action action = patternValidations.getAction(scanner.next(), mapOfSavedVariables);
        return buildExpression(action, scanner, mapOfSavedVariables);
    }

    private Action buildExpression(Action previousValueAction, Scanner scanner, HashMap<Character, Integer> mapOfSavedVariables) {
        if (scanner.hasNext()) {
            switch (scanner.next()) {
                case PLUS_STRING_VALUE:
                    return new Expression(previousValueAction, Operators.ADD, buildExpression(scanner, mapOfSavedVariables));
                case MINUS_STRING_VALUE:
                    return new Expression(previousValueAction, Operators.SUBTRACT, buildExpression(scanner, mapOfSavedVariables));
                case MULTIPLY_STRING_VALUE:
                    Action multiplyExpression = new Expression(previousValueAction, Operators.MULTIPLY, patternValidations.getAction(scanner.next(), mapOfSavedVariables));
                    if (scanner.hasNext()) {
                        return buildExpression(multiplyExpression, scanner, mapOfSavedVariables);
                    }
                    return multiplyExpression;
            }
        }
        return previousValueAction;
    }
}