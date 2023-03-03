package edu.ntnu.trym.exercise4.service;

import edu.ntnu.trym.exercise4.model.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    Logger logger = LoggerFactory.getLogger(CalculationService.class);

    public double calculate(Expression expression) {
        double baseVal = Double.parseDouble(expression.elements().remove(0));
        while(!expression.elements().isEmpty()) {
            baseVal = switch (expression.elements().remove(0)) {
                case "x" -> {
                    logger.info("Multiplication operation was used");
                    yield baseVal * Double.parseDouble(expression.elements().remove(0));
                }
                case "/" -> {
                    logger.info("Division operation was used");
                    yield baseVal / Double.parseDouble(expression.elements().remove(0));
                }
                case "+" -> {
                    logger.info("Addition operation was used");
                    yield baseVal + Double.parseDouble(expression.elements().remove(0));
                }
                case "-" -> {
                    logger.info("Subtraction operation was used");
                    yield baseVal - Double.parseDouble(expression.elements().remove(0));
                }
                default -> throw new IllegalArgumentException("The operator is invalid");
            };
        }

        return baseVal;
    }

}
