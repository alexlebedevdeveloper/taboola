package tasks.questionone.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntroService {
    private static final Logger logger = LoggerFactory.getLogger(IntroService.class);

    public void welcomeExpression() {
        logger.info("Welcome to text based calculator application.\n");
        logger.info("The input is a series of assignment expressions. The syntax is a subset of Java numeric expressions and operators\n");
        logger.info("At the end of evaluating the series, the value of each variable is printed out.\n");
        logger.info("In order to end statement - press blank Enter. \n");
    }
}
