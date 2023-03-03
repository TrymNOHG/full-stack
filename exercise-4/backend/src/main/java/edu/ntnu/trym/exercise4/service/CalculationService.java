package edu.ntnu.trym.exercise4.service;

import edu.ntnu.trym.exercise4.model.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    Logger logger = LoggerFactory.getLogger(CalculationService.class);

    public double calculate(Expression expression) {
        switch (expression.operator()) {
            case "*" -> {
                logger.info("Multiplication operation was used");
                return expression.num1() * expression.num2();
            }
            case "/" -> {
                logger.info("Division operation was used");
                return expression.num1() / expression.num2();
            }
            case "+" -> {
                logger.info("Addition operation was used");
                return expression.num1() + expression.num2();
            }
            case "-" -> {
                logger.info("Subtraction operation was used");
                return expression.num1() - expression.num2();
            }
            default -> throw new IllegalArgumentException("The operator is invalid");
        }
    }

}
