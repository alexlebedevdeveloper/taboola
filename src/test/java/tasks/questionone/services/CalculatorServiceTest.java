package tasks.questionone.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tasks.questionone.calculator.Calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest{

    private static final String I_VAR = "i";
    private static final String J_VAR = "j";
    private CalculatorService calculatorService = new CalculatorService();

    private Calculator calculator = new Calculator();

    @Test
    public void testCalculatorServiceWithSingleSimpleInput(){
        calculatorService.evaluateExpression(calculator, "i = 2 + 5");
        assertTrue(calculator.getSavedEntryVariables().containsKey(I_VAR.charAt(0)));
        assertEquals(0, calculator.getSavedEntryVariables().get(I_VAR.charAt(0)).compareTo(7));
        calculator.printCalculatedResults();
    }

    @Test
    public void testCalculatorServiceWithMultipleEntries(){
        calculatorService.evaluateExpression(calculator, "i = 2 + 5");
        calculatorService.evaluateExpression(calculator, "j = i++");
        calculatorService.evaluateExpression(calculator, "i = i + 2");
        calculatorService.evaluateExpression(calculator, "j += 1");
        assertTrue(calculator.getSavedEntryVariables().containsKey(I_VAR.charAt(0)));
        assertEquals(0, calculator.getSavedEntryVariables().get(I_VAR.charAt(0)).compareTo(10));
        assertTrue(calculator.getSavedEntryVariables().containsKey(J_VAR.charAt(0)));
        assertEquals(0, calculator.getSavedEntryVariables().get(J_VAR.charAt(0)).compareTo(8));
        calculator.printCalculatedResults();
    }

    @Test
    public void testCalculatorServiceWithZeroEntries(){
        assertEquals(0, calculator.getSavedEntryVariables().keySet().size());
        calculator.printCalculatedResults();
    }

    @Test
    public void testCalculatorServiceWithInvalidEntry(){
        calculatorService.evaluateExpression(calculator, "i = 2 + 5df");
        assertEquals(0, calculator.getSavedEntryVariables().keySet().size());
        calculator.printCalculatedResults();
    }

    @Test
    public void testCalculatorServiceWithMultipleEntriesAndOneInvalid(){
        calculatorService.evaluateExpression(calculator, "i = 2 + 5");
        calculatorService.evaluateExpression(calculator, "j = i++");
        calculatorService.evaluateExpression(calculator, "i = i + 2");
        calculatorService.evaluateExpression(calculator, "j += 1");
        calculatorService.evaluateExpression(calculator, "i = 2 + 5df");
        assertTrue(calculator.getSavedEntryVariables().containsKey(I_VAR.charAt(0)));
        assertEquals(0, calculator.getSavedEntryVariables().get(I_VAR.charAt(0)).compareTo(10));
        assertTrue(calculator.getSavedEntryVariables().containsKey(J_VAR.charAt(0)));
        assertEquals(0, calculator.getSavedEntryVariables().get(J_VAR.charAt(0)).compareTo(8));
        calculator.printCalculatedResults();
    }

}
