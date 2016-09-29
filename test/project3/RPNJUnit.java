package project3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class RPNJUnit {
    
    @Test
    public void answerTesting () {
        assertEquals(14, RPN.calculateFromString("5 1 2 + 4 * 3 - +"), 0.01);
        assertEquals(3170, RPN.calculateFromString("42 3 + 5 * 6 3 / 2 % "
                + "- 3000 + 4000 3 % - 4 2 / + 14 + 7 3 4 * * - 14 +"), 0.01);
        assertEquals(-20, RPN.calculateFromString("42 62 -"), 0.01);
        assertEquals(141, RPN.calculateFromString("42 3 * 15 + 6 5 - *"), 0.01);
        assertEquals(-55, RPN.calculateFromString("42 3 + 5 * 6 3 / 2 % - 3000 "
                + "+ 4000 - 3 % 4 2 / + 14 + 7 3 4 * * - 14 +"), 0.01);
        assertEquals(-108, RPN.calculateFromString("5 17 - 52 8 9 6 7 "
                + "* + - + *"), 0.01);
    }
    
    @Test(expected = InvalidRPNStringException.class)
    public void tooFewOperands() {  
        RPN.calculateFromString("5 2 + +");
    }
    
    @Test(expected = InvalidRPNStringException.class)
    public void divisionByZero() {        
        RPN.calculateFromString("5 0 /");
        RPN.calculateFromString("5 0 %");
    }
    
    @Test(expected = InvalidRPNStringException.class)
    public void invalidCharacters() {
        RPN.calculateFromString("hi mom");
        RPN.calculateFromString("5 . 1 +");
        RPN.calculateFromString("5 d +");
    }
    
    @Test(expected = InvalidRPNStringException.class)
    public void tooManyOperands() {
        RPN.calculateFromString("5 5 + +");
        RPN.calculateFromString("5 5 5 - / 5 - -");
    }
}