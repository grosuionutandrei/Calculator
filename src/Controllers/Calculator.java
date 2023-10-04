package Controllers;

import java.util.Stack;

public class Calculator {

    public Calculator(){

    }
    public double calculateExpression(Stack<String> calculus) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        while (calculus.size()>0) {
            String current = calculus.pop();

            if (isDigit(current)) {
                numbers.push(Double.parseDouble(current));
            } else if (isOperator(current)) {
                while (!operators.isEmpty() && !hasHigherPrecedence(current, operators.peek())) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    String operator = operators.pop();
                    double result = applyOperator(a, b, operator);
                    numbers.push(result);
                }
                operators.push(current);
            } else {
                System.out.println("Error: Invalid character in expression: " + current);
                return Double.NaN;
            }
        }

        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            String operator = operators.pop();
            double result = applyOperator(a, b, operator);
            numbers.push(result);
        }

        if (numbers.size() == 1) {
            return numbers.pop();
        } else {
            System.out.println("Error: Invalid expression");
            return Double.NaN;
        }
    }

    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }
    private boolean isDigit(String toCheck){
        try {
            Double.parseDouble(toCheck);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean hasHigherPrecedence(String op1, String op2) {
        return (op1.equals("*") || op1.equals("/")) && (op2.equals("+")|| op2.equals("-"));
    }

    private double applyOperator(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b != 0) {
                    return a / b;
                } else {
                    System.out.println("Error: Division by zero");
                    return Double.NaN;
                }
            default:
                return Double.NaN;
        }
    }
}


