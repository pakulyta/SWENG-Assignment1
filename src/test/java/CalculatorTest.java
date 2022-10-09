//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void nums_opsTest() {
        // valid input
        ArrayList<String> expected = new ArrayList<>();
        expected.add("7");
        expected.add("+");
        expected.add("3");

        String testInput = "7+3";
        assertEquals(expected,Calculator.nums_ops(testInput),"Valid input string does not match.");

        // longer valid input
        expected.clear();
        expected.add("2");
        expected.add("+");
        expected.add("3");
        expected.add("*");
        expected.add("4");
        expected.add("-");
        expected.add("6");

        testInput = "2+3*4-6";
        assertEquals(expected,Calculator.nums_ops(testInput),"Longer valid input string does not match.");

        // only chars
        expected.clear();
        expected.add("-1");

        testInput = "hello";
        assertEquals(expected,Calculator.nums_ops(testInput),"Only chars input string does not match.");

        // too many operators
        testInput = "2-3++3";
        assertEquals(expected,Calculator.nums_ops(testInput),"Too many operators input string does not match.");

        // trailing operator
        testInput = "5-3+44*";
        assertEquals(expected,Calculator.nums_ops(testInput),"Trailing operator input string does not match.");

        // unsupported operator
        testInput = "23-456+32/45";
        assertEquals(expected,Calculator.nums_ops(testInput),"Unsupported operator input string does not match.");

        // expression too short
        testInput = "2-";
        assertEquals(expected,Calculator.nums_ops(testInput),"Expression too short input string does not match.");
    }

    @Test
    void calculationTest() {
        // valid input
        ArrayList<String> testInput = new ArrayList<>();
        testInput.add("7");
        testInput.add("+");
        testInput.add("3");

        String testOut = Calculator.calculation(testInput);
        assertEquals("10",testOut,"Valid input alculation is wrong.");

        // longer valid input
        testInput.clear();
        testInput.add("2");
        testInput.add("+");
        testInput.add("3");
        testInput.add("*");
        testInput.add("4");
        testInput.add("-");
        testInput.add("6");

        testOut = Calculator.calculation(testInput);
        assertEquals("8",testOut,"Longer valid input calculation is wrong.");
    }
}