package edu.ntnu.trym.exercise4.controller;

import edu.ntnu.trym.exercise4.model.Expression;
import edu.ntnu.trym.exercise4.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculationController {

    private final CalculationService calculationService;
    Logger logger = LoggerFactory.getLogger(CalculationController.class);


    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    @CrossOrigin(origins="*")
    @ResponseBody
    public double calculate(@RequestBody Expression expression) {
        try {
            double result = this.calculationService.calculate(expression);
            logger.info("The expression yielded: " + result);
            return result;
        } catch(IllegalArgumentException e) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

}
//TODO: look at ResponseEntity<T>