package project3;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
  * This class implements the calculateFromString() method using
  * Reverse Polish Notation (RPN)
  *
  * @author James Osborne
  * @version 1.0  
  * File: Project3.java
  * Created:  Sept 26 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     26 Sept 2016 – JAO – Created calculateFromString() method, set up switch
  *     statement, and had input coming from a main. Was calculating correctly.
  *     27 Sept 2016 - JAO - Started error testing and set up try-catch.
  *     28 Sept 2016 - JAO - Removed main method and set up jUnit testing
  *     06 Oct  2016 - JAO - Removed decimal formatting and println(). Fixed
  *     various issues regarding the wrong message being given to exceptions.
  * 
  * Description: This class provides a static method for calculating doubles
  * with any of the basic operators (+, -, *, /) by using RPN. This method
  * throws an InvalidRPNString exception for any issue that occurs,
  * but it has a custom message regarding the situation.
  */
public class RPNCalculator {    
    
    /**
      * Calculates basic mathematic operations (+, -, *, /) from a String
      * using Reverse Polish Notation (RPN).
      *
      * @param input The String which defines the operators and operands
      * of the calculation.
      * @return A double holding the result of the calculation.
      * @throws InvalidRPNStringException If not enough operators, operands,
      * division by zero occurs, or improper input is given.
      */
    public double calculateFromString(String input) {
        //Scanner used for parsing the input String.
        Scanner scan = new Scanner(input);
        double answer;
        //Stores the operands for the actual calculation.
        double operandOne;
        double operandTwo;
        //Stores operands before they are calculated and gets the calculation
        //results pushed back onto it.
        Stack<Double> operands = new Stack<Double>();
        
        try {
            //As long as the input String has something for the method to read,
            //it will continue parsing.
            while (scan.hasNext()) {
                //Checks for numbers to be pushed onto the stack.
                if (scan.hasNextDouble()) {
                    double num = scan.nextDouble();
                    operands.push(num);
                } 
                else {
                    String operator = scan.next();
                    
                    //Since a number was not read, what was read is assumed
                    //to be an operator and will be checked through a switch()
                    switch (operator) {
                        //The top of the stack should be the second operaand
                        //due to the LIFO (Last In, First Out) nature of a stack
                        case "+":
                            operandTwo = operands.pop();
                            operandOne = operands.pop();
                            
                            operands.push(operandOne + operandTwo);
                            break;

                        case "-":
                            operandTwo = operands.pop();
                            operandOne = operands.pop();
                            
                            operands.push(operandOne - operandTwo);
                            break;

                        case "*":
                            operandTwo = operands.pop();
                            operandOne = operands.pop();
                            
                            operands.push(operandOne * operandTwo);
                            break;

                        case "/":
                            operandTwo = operands.pop();
                            operandOne = operands.pop();
                            
                            //If division by zero is attempted, the proper
                            //message is given to the InvalidRPNStringException.
                            if (operandTwo == 0.0) {
                                throw new InvalidRPNStringException("Division "
                                        + "by zero is not allowed");
                            }
                            
                            operands.push(operandOne / operandTwo);
                            break;
                            
                        //If the encountered String was none of the operators,
                        //the input must have been improper and an
                        //InvalidRPNStringException is thrown.
                        default:
                            throw new InvalidRPNStringException("Only numbers "
                                    + "and operators (+, -, *, /) delimited by "
                                    + "single spaces are valid input");
                    }
                }
            }
        }
        
        //If an EmptyStackException is thrown, it is caught here so it can be
        //re-thrown with an explanatory exception to the user. This hides the
        //inner workings of the RPN calculator, for a user does not need
        //to know that we are using a stack and that was what went wrong.
        catch (EmptyStackException e) {
            throw new InvalidRPNStringException("Not enough operands");
        }
        
        //Only if the string is completely empty, or has only whitespace,
        //will this condition be true
        if (operands.isEmpty()) {
            throw new InvalidRPNStringException("Empty input is not valid");
        }
        //If there is more than one number in the stack, not enough operators 
        //were present in the string. An answer can only be a single number.
        if (operands.size() > 1) {
            throw new InvalidRPNStringException("Not enough operators");
        }
 
        answer = operands.pop();
        
        return answer; 
    }
}