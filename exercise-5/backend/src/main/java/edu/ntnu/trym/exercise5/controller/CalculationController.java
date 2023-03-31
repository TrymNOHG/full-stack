package edu.ntnu.trym.exercise5.controller;

import edu.ntnu.trym.exercise5.dto.ExpressionDTO;
import edu.ntnu.trym.exercise5.dto.UserDTO;
import edu.ntnu.trym.exercise5.model.Equation;
import edu.ntnu.trym.exercise5.model.User;
import edu.ntnu.trym.exercise5.repo.EquationRepository;
import edu.ntnu.trym.exercise5.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/calculator")
public class CalculationController {

    private final CalculationService calculationService;

    Logger logger = LoggerFactory.getLogger(CalculationController.class);


    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    @CrossOrigin(origins="*")
    @ResponseBody
    public ResponseEntity<Double> calculate(@RequestBody ExpressionDTO expression,
                                            Authentication authentication) {
        try {
            double result = calculationService.saveExpression(expression, authentication.getName());
            logger.info("The expression yielded: " + result);
            logger.info("The expression has been saved");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}