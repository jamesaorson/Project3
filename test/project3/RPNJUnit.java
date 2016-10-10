package project3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class RPNJUnit {
    private RPNCalculator rpn = new RPNCalculator();
    
    //These tests simply test the accuracy of the calcluator, making sure
    //it can handle all of the basic operations on their own
    //and in complex combinations.
    @Test
    public void answerTesting() {
        assertEquals(10, rpn.calculateFromString("5 5 +"), 0.000001);
        assertEquals(10, rpn.calculateFromString("20 10 -"), 0.000001);
        assertEquals(100, rpn.calculateFromString("10 10 *"), 0.000001);
        assertEquals(10, rpn.calculateFromString("100 10 /"), 0.000001);
        assertEquals(14, rpn.calculateFromString("5 1 2 + 4 * "
                                                 + "3 - +"), 0.000001);
        assertEquals(-20, rpn.calculateFromString("42 62 -"), 0.000001);
        assertEquals(141, rpn.calculateFromString("42 3 * 15 + "
                                                  + "6 5 - *"), 0.000001);
        assertEquals(-108, rpn.calculateFromString("5 17 - 52 8 9 6 7 "
                                                   + "* + - + *"), 0.000001);
        assertEquals(1.125, rpn.calculateFromString("1 6 2 + + 15 + 7 - "
                                                    + "8 - 8 /"), 0.000001);
    }
    
    @Rule public ExpectedException expected = ExpectedException.none();
    
    //This tests the ability for the calculator to detect a lack of
    //necessary operands and throws the correct exception message.
    @Test
    public void tooFewOperands() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Not enough operands");
        
        rpn.calculateFromString("5 2 + +");
    }
    
    //This tests the ability for the calculator to detect a lack of
    //necessary operators and throws the correct exception message.
    @Test
    public void tooFewOperators() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Not enough operators");
        
        rpn.calculateFromString("5 5 5 +");
    }

    //This tests the ability for the calculator to detect the specific use
    //of an empty string.
    @Test
    public void emptyInputString() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Empty input is not valid");
         
        rpn.calculateFromString("");
    }
    
    //This tests the ability for the calculator to detect
    //an attempt at division by zero.
    @Test
    public void divisionByZero() {        
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Division by zero is not allowed");
        
        rpn.calculateFromString("5 0 /");
    }
    
    //This tests the ability for the calculator to detect input which includes
    //characters other than numbers and the approved operators (+, -, *, /)
    @Test
    public void invalidCharacters() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Only numbers and operators (+, -, *, /) "
                               + "delimited by whitespace are valid input");
        
        rpn.calculateFromString("hi mom");
    }
}