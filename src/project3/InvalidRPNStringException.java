package project3;

/**
  * This class implements the InvalidRPNStringException for the RPNCalculator.
  *
  * @author James Osborne
  * @version 1.0  
  * File: InvalidRPNStringException.java
  * Created:  Sept 26 2016
  * ©Copyright James Osborne. All rights reserved.
  * Summary of Modifications:
  *     26 Sept 2016 – JAO – Created constructor
  * 
  * Description: This class provides an exception for all possible errors
  * for the RPNCalculator: division by zero, invalid input characters,
  * not enough operands, not enough operators, and empty input.
  */
public class InvalidRPNStringException extends RuntimeException {
    InvalidRPNStringException(String err) {
        super(err);
    }
}
