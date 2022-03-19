package tasks.questionone.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tasks.questionone.calculator.Calculator;
import tasks.questionone.exceptions.InvalidStatementException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public void activateCalculator() throws IOException {
        Calculator calculator = new Calculator();
        compileExpression(calculator);
        calculator.printCalculatedResults();
    }

    private void compileExpression(Calculator calculator) throws IOException {
        while (true) {
            String s = getInputFromUser();
            if (s == null) break;
            evaluateExpression(calculator, s);
        }
    }

    protected void evaluateExpression(Calculator calculator, String s) {
        try {
            calculator.calculate(s);
        } catch (InvalidStatementException e) {
            logger.warn("Invalid statement. Please try again.");
        } catch (UnsupportedOperationException e) {
            logger.warn("You used an unsupported operation. Please try again.");
        } catch (Exception e) {
            logger.error("unexpected exception happened.", e);
        }
    }

    private String getInputFromUser() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        logger.info("Please write logical statement: ");
        String input = br.readLine();
        return input == null || input.isEmpty() ? null : input;
    }
}
