package tasks.questionone;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import tasks.questionone.services.CalculatorService;
import tasks.questionone.services.IntroService;

import java.io.IOException;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        CalculatorServiceCall call = applicationContext.getBean(CalculatorServiceCall.class);
        call.startCalculator();
    }

    @Service
    class CalculatorServiceCall {
        private IntroService introService = new IntroService();
        private CalculatorService calculatorService = new CalculatorService();

        public void startCalculator() throws IOException {
            introService.welcomeExpression();
            calculatorService.activateCalculator();
        }
    }
}
