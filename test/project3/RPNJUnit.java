package project3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class RPNJUnit {
    private RPNCalculator rpn = new RPNCalculator();
    
    @Test
    public void answerTesting () {
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
    
    @Test
    public void tooFewOperands() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Not enough operands");
        
        rpn.calculateFromString("5 2 + +");
    }
    
    @Test
    public void emptyInputString() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Empty input is not valid");
         
        rpn.calculateFromString("");
    }
    
    @Test
    public void tooFewOperators() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Not enough operators");
        
        rpn.calculateFromString("5 5 5 +");
    }
    
    @Test
    public void divisionByZero() {        
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Division by zero is not allowed");
        
        rpn.calculateFromString("5 0 /");
    }
    
    @Test
    public void invalidCharacters() {
        expected.expect(InvalidRPNStringException.class);
        expected.expectMessage("Only numbers and operators (+, -, *, /) "
                           + "delimited by single spaces are valid input");
        
        rpn.calculateFromString("hi mom");
    }
}