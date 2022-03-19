package tasks.questionone.common;

import tasks.questionone.dto.Expression;
import tasks.questionone.dto.Operators;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

class PatternValidations {

    private static final String A_Z = "[a-z]";
    private static final String PLUS = "\\+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "\\*";
    private static final String DIGITS = "[0-9]+";
    private static final String PRE_INCREMENT = "\\+\\+[a-z]";
    private static final String POST_INCREMENT = "[a-z]\\+\\+";
    private static final String EQUALS = "=";
    static final String INCREMENT_EQUALS_REGEX = "\\+=";
    static final String INCREMENT_EQUALS = "+=";

    Action getAction(String s, HashMap<Character, Integer> mapOfSavedVariables) {
        if (isDigitEntry(s)) return new SimpleAction(Integer.parseInt(s));
        if (isAlphabeticEntry(s)) return new IncrementActionExpression(s.charAt(0), false, mapOfSavedVariables);
        if (isPostIncrementEntry(s)) return new IncrementActionExpression(s.charAt(0), true, mapOfSavedVariables);
        if (isPreIncrementEntry(s))
            return new Expression(new SimpleAction(1), Operators.ADD, new IncrementActionExpression(s.charAt(2), true, mapOfSavedVariables));

        return null;
    }

    boolean isInvalid(String expr) {
        try(Scanner scanner = new Scanner(expr)) {
            if (!scanner.hasNext() || !Pattern.matches(A_Z, scanner.next())) return true;
            if (!scanner.hasNext() || !isAssignment(scanner.next())) return true;
            return isContextInvalid(scanner);
        }
    }

    private boolean isContextInvalid(Scanner scanner) {
        boolean invalid = true;
        while (scanner.hasNext()) {
            String characterStringValue = scanner.next();
            if (!invalid) {
                if (!isOperator(characterStringValue)) {
                    return true;
                }
                invalid = true;
            } else {
                if (!isValidValueEntry(characterStringValue)) {
                    return true;
                }
                invalid = false;
            }
        }
        return invalid;
    }

    private boolean isAssignment(String s) {
        return Pattern.matches(EQUALS, s) || Pattern.matches(INCREMENT_EQUALS_REGEX, s);
    }

    private boolean isOperator(String s) {
        return (Pattern.matches(PLUS, s))
                || Pattern.matches(MINUS, s)
                || Pattern.matches(MULTIPLY, s);
    }

    private boolean isValidValueEntry(String s) {
        return isDigitEntry(s) || isAlphabeticEntry(s) || isPreIncrementEntry(s) || isPostIncrementEntry(s);
    }

    private boolean isDigitEntry(String s) {
        return Pattern.matches(DIGITS, s);
    }

    private boolean isAlphabeticEntry(String s) {
        return Pattern.matches(A_Z, s);
    }

    private boolean isPreIncrementEntry(String s) {
        return Pattern.matches(PRE_INCREMENT, s);
    }

    private boolean isPostIncrementEntry(String s) {
        return Pattern.matches(POST_INCREMENT, s);
    }
}
