package basics;

/*
 * Basic Test file to demonstrate:
 *  -Basic Annotations
 *  -Assertions
 *  -Simple testing approach for expected exceptions
 */
import com.revature.junit.basics.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    /*
     * @BeforeEach runs before EVERY test method
     *
     * It ensures that each test starts with a fresh,
     * predictable instance rather than reusing state
     * changes between tests
     */
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /*
     * @Test declares that a method is a test method
     * @DisplayName controls the descriptive text shown when running a test
     */
    @Test
    @DisplayName("Adding two positive numbers (2, 3) returns their sum")
    void testAdd_01() {
        // Arrange
        int param1 = 2;
        int param2 = 3;
        int expectedValue = 5;

        // Act
        int result = calculator.add(param1, param2);

        // Assert
        // assertEquals(expected, actual)
        assertEquals(expectedValue, result);
    }

    @Test
    @DisplayName("Adding two positive numbers (5, 7) returns their sum")
    void testAdd_02() {
        int result = calculator.add(5, 7);

        // assertEquals(expected, actual)
        assertEquals(12, result);
    }

    @Test
    @DisplayName("Subtracting two numbers returns the correct difference")
    void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("Multiplying two numbers returns the correct product")
    void testMultiply() {
        int result = calculator.multiply(6, 7);
        assertEquals(42, result);
    }

    @Test
    @DisplayName("Dividing two numbers returns the correct quotient")
    void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    @DisplayName("Dividing by zero throws an ArithmeticException")
    void testDivideByZero() {
        /*
          * assertThrows verifies that the given code block throws the
          * expected exception type.
          *
          * It then returns that exception to allow inspection if needed
         */
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0)  // Calling our divice method with (10 and 0 arguments i.e. 10/0)
        );

        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    /*
     * Use '@Disabled' to skip tests - typically used when there is a
     * known bug, but we still want to test the rest of our code
     */
    @Disabled("Disabled until bug #1234 is fixed")
    @Test
    @DisplayName("This Test will be skipped")
    void testToBeSkipped() {
        assertEquals(true, false);
    }
}
