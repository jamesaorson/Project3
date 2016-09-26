package project3;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Owner
 */
public class RPN {
    public static void main(String [] args) {
        String s = "23.3 5 16.2 + 8 * -";
        
        calculateFromString(s);
    }
    
    public static double calculateFromString(String input) {
        Scanner scan = new Scanner(input).useDelimiter(" ");
        double answer;
        double operandOne;
        double operandTwo;
        Stack nums = new Stack();
        
        while (scan.hasNext()) {
            if (scan.hasNextDouble()) {
                double num = scan.nextDouble();
                nums.push(num);
            } 
            else {
                String operator = scan.next();
                switch (operator) {
                    case "+":
                        operandTwo = (double) nums.pop();
                        operandOne = (double) nums.pop();
                        
                        nums.push(operandOne + operandTwo);
                        break;
                        
                    case "-":
                        operandTwo = (double) nums.pop();
                        operandOne = (double) nums.pop();
                        
                        nums.push(operandOne - operandTwo);
                        break;
                        
                    case "*":
                        operandTwo = (double) nums.pop();
                        operandOne = (double) nums.pop();
                        
                        nums.push(operandOne * operandTwo);
                        break;
                        
                    case "/":
                        operandTwo = (double) nums.pop();
                        operandOne = (double) nums.pop();
                        
                        nums.push(operandOne / operandTwo);
                        break;
                        
                    default:
                        break;
                }
            }
        }
        answer = (double) nums.pop();
        
        DecimalFormat formatter = new DecimalFormat("#0.00");
        System.out.println(formatter.format(answer));
        
        return 0.0;
    }
}
