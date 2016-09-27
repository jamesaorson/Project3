package project3;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Owner
 */
public class RPN {
    private static double answer = 0.0;
    private static double operandOne;
    private static double operandTwo;
    private static Stack<Double> operands = new Stack<Double>();
    
    public static void main(String [] args) {
        String s = "23.3 5 16.2 10 10 10 + 8 * -";
        
        calculateFromString(s);
    }
    
    public static double calculateFromString(String input) {
        Scanner scan = new Scanner(input).useDelimiter(" ");
        
        try {
            while (scan.hasNext()) {
                if (scan.hasNextDouble()) {
                    double num = scan.nextDouble();
                    operands.push(num);
                } 
                else {
                    String operator = scan.next();
                    switch (operator) {
                        case "+":
                            determineOperands();

                            operands.push(operandOne + operandTwo);
                            break;

                        case "-":
                            determineOperands();

                            operands.push(operandOne - operandTwo);
                            break;

                        case "*":
                            determineOperands();

                            operands.push(operandOne * operandTwo);
                            break;

                        case "/":
                            determineOperands();
                            
                            if (operandTwo == 0.0) {
                                throw new InvalidRPNStringException("Divison "
                                        + "by zero is not allowed");
                            }
                            
                            operands.push(operandOne / operandTwo);
                            break;

                        default:
                            throw new InvalidRPNStringException("Only numbers "
                                    + "and these operators (+, -, *, /) "
                                    + "are valid input");
                    }
                }
            }
        }
        
        catch (EmptyStackException e) {
            throw new InvalidRPNStringException("Too many operators or not "
                    + "enough operands in string");
        }
        
        if (operands.size() > 1) {
            throw new InvalidRPNStringException("Too many operands in string");
        }
        
        if (!operands.empty()) {
            answer = operands.pop();
        }
        
        DecimalFormat formatter = new DecimalFormat("#0.00");
        System.out.println(formatter.format(answer));
        
        return answer;        
    }
    
    private static void determineOperands() {
        if (operands.size() < 2) {
            throw new InvalidRPNStringException("Not "
                    + "enough operands");
        }
                            
        operandTwo = operands.pop();
        operandOne = operands.pop();
    }
}