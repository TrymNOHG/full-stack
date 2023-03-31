package edu.ntnu.trym.exercise5.service;

import edu.ntnu.trym.exercise5.dto.EquationDTO;
import edu.ntnu.trym.exercise5.dto.ExpressionDTO;
import edu.ntnu.trym.exercise5.dto.UserDTO;
import edu.ntnu.trym.exercise5.exception.UserNotFoundException;
import edu.ntnu.trym.exercise5.mapper.CalculationMapper;
import edu.ntnu.trym.exercise5.model.Equation;
import edu.ntnu.trym.exercise5.model.User;
import edu.ntnu.trym.exercise5.repo.EquationRepository;
import edu.ntnu.trym.exercise5.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationService {

    private final EquationRepository equationRepository;
    private final CalculationMapper calculationMapper;
    private final Logger logger = LoggerFactory.getLogger(CalculationService.class);
    private final UserRepository userRepository;

    public CalculationService(EquationRepository equationRepository, CalculationMapper calculationMapper,
                              UserRepository userRepository) {
        this.equationRepository = equationRepository;
        this.calculationMapper = calculationMapper;
        this.userRepository = userRepository;
    }

    public double calculate(ExpressionDTO expression) {
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

    public double saveExpression(ExpressionDTO expression, String name) {
        User user = userRepository.findUserByUsername(name)
                .orElseThrow(() -> new UserNotFoundException(name));
        double result = calculate(new ExpressionDTO(new ArrayList<>(expression.elements())));
        Equation equation = equationRepository.save(calculationMapper.toEquation(expression, result, user));
        System.out.println(equation);
        logger.info("Expression saved");
        return result;
    }

    public List<EquationDTO> getAllEquations(UserDTO user) {
        return equationRepository.findAllByUser_Username(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername()))
                .stream().map(CalculationMapper::toEquationDTO).toList();
    }

    public List<ExpressionDTO> getAllExpressions() {
        List<Equation> equations = equationRepository.findAll();
        return  transformEquationListToExpression(equations);
    }

    private List<ExpressionDTO> transformEquationListToExpression(List<Equation> equations) {
        return equations
                .stream()
                .map(calculationMapper::toExpression)
                .toList();
    }

}
