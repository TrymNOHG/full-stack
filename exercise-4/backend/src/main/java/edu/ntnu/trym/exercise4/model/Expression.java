package edu.ntnu.trym.exercise4.model;

/**
 * This record represents an expression. It takes in the intrinsic parts of a simple expression.
 *
 * @param num1     The first constant, represented as a double.
 * @param num2     The second constant, represented as a double.
 * @param operator The operator of the equation, given as a String.
 *
 * @author Trym Hamer Gudvangen
 */
public record Expression(double num1, double num2, String operator) {
    
}
