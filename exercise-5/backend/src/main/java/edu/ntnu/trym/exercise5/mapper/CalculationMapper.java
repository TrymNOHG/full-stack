package edu.ntnu.trym.exercise5.mapper;


import edu.ntnu.trym.exercise5.dto.EquationDTO;
import edu.ntnu.trym.exercise5.dto.ExpressionDTO;
import edu.ntnu.trym.exercise5.model.Equation;
import edu.ntnu.trym.exercise5.model.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CalculationMapper {

    public Equation toEquation(ExpressionDTO expression, double result, User user) {
        String expressionString = String.join(" ", expression.elements());
        return Equation
                .builder()
                .expression(expressionString)
                .result(result)
                .user(user)
                .build();
    }

    public ExpressionDTO toExpression(Equation equation) {
        String equationString = equation.getExpression() + " " + equation.getResult();
        return new ExpressionDTO(Arrays.stream(equationString.split(" ")).toList());
    }

    public static EquationDTO toEquationDTO(Equation equation) {
        return EquationDTO
                .builder()
                .expression(equation.getExpression())
                .result(equation.getResult())
                .build();
    }

}
