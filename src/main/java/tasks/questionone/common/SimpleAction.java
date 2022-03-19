package tasks.questionone.common;


import java.util.HashMap;

public class SimpleAction extends Action {
    private final int value;

    SimpleAction(int value) {
        this.value = value;
    }

    @Override
    public int evaluateAction(HashMap<Character, Integer> increments) {
        return value;
    }
}