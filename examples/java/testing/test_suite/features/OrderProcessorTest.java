package features;

import com.revature.junit.features.OrderProcessor;
import com.revature.junit.features.Order;

// Annotation Imports from jupter.api dependency
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

// Annotation Imports from jupiter.params dependency
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

// This is a Static import
// The keyword 'static' can be used as part of an Import statement
// when referencing a static resource (i.e. static method/class/etc...)
// A static import allows you to reference the resource directly
// as if it were declared in the same class!
// i.e. instead of 'Assertions.assertEquals()'
// assertEquals()
import static org.junit.jupiter.api.Assertions.*;

/*
 * Test Class showcasing:
 * - @Nested annotation: Used to nest test classes - organizes
 *          related tests
 * - @ParameterizedTest: Used to pass inputs into a test
 * - Additional assert methods
 */
public class OrderProcessorTest {

    private OrderProcessor processor;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting OrderProcessor test suite...");
    }

    @BeforeEach
    void setUp() {
        processor = new OrderProcessor();
    }

    /*
     * @Nested: groups related tests into a logical subclass,
     * which also shows up clearly organized in test reports
     */
    @Nested
    @DisplayName("Discount calculation tests")
    class DiscountTests {

        @Test
        @DisplayName("No discount applied when subtotal is below threshold")
        void testNoDiscountBelowThreshold() {
            double discount = processor.calculateDiscount(50.00);
            assertEquals(0.0, discount);
        }

        @Test
        @DisplayName("Discount applied when subtotal meets threshold")
        void testDiscountAtThreshold() {
            double discount = processor.calculateDiscount(100.00);
            assertEquals(10.00, discount, 0.001); // delta for double comparison
        }

        /*
         * @ParameterizedTest runs the same test method multiple times,
         * once for each value supplied
         *
         * This allows you to avoid  writing near-identical test methods
         * for each input case
         *
         * A '@[Type]Source' annotation should be used with your parameterized
         * tests to provide the test arguments on each test execution
         *
         * @ValueSource is used to supply simple values
         * Syntax:
         *      @ValueSource(DataType = {value1, value2, etc...})
         * @ValueSource only supports these types:
         *
         * Data Type | Attribute Name
         *  - short | 'shorts' - i.e. @ValueSource(shorts = {1, 2, 3})
         *  - byte | 'bytes'
         *  - int | 'ints'
         *  - long | 'longs'
         *  - float | 'doubles'
         *  - double | 'doubles'
         *  - char | 'chars'
         *  - java.long.String | 'strings'
         *  - java.lang.Class | 'classes'
         */
        @ParameterizedTest
        @ValueSource(doubles = {100.00, 150.00, 200.00, 500.00})
        @DisplayName("Discount is always 10% when subtotal is at or above threshold")
        void testDiscountAboveThreshold(double subtotal) {
            double discount = processor.calculateDiscount(subtotal);
            // The 0.001 is for a 'delta'
            // This gives you some wiggle room when performing decimal calculations
            // in Java (to avoid bugs that are unrelated to your code, but rather
            // ones that are attributed to how decimal point math is performed using
            // binary data..."Decimal Point Precision"
            assertEquals(subtotal * 0.10, discount, 0.001);
        }

        /*
         * @CsvSource supplies multiple related values per test run
         * here, an input subtotal paired with its expected discount
         *
         * With @CsvSource, a comma is the default delimiter, but you
         * can supply a different one using the 'delimiter' attribute:
         *
         * @CsvSource( { "key:value", "key:value" }, delimiter=':' )
         */
        @ParameterizedTest
        @CsvSource({
                "50.00, 0.00",
                "99.99, 0.00",
                "100.00, 10.00",
                "250.00, 25.00"
        })
        @DisplayName("Discount matches expected value across a range of subtotals")
        void testDiscountVariousSubtotals(double subtotal, double expectedDiscount) {
            double discount = processor.calculateDiscount(subtotal);
            assertEquals(expectedDiscount, discount, 0.001);
        }
    } // End of 'Discount' Tests

    @Nested
    @DisplayName("Order validation tests")
    class ValidationTests {

        @Test
        @DisplayName("Valid order passes validation")
        void testValidOrder() {
            Order order = new Order("CUST-001", 75.00, 3);
            assertTrue(processor.isValidOrder(order));
        }

        @Test
        @DisplayName("Order with blank customer ID fails validation")
        void testBlankCustomerId() {
            Order order = new Order("", 75.00, 3);
            assertFalse(processor.isValidOrder(order));
        }

        @Test
        @DisplayName("Order exceeding max item count fails validation")
        void testExceedsMaxItemCount() {
            Order order = new Order("CUST-002", 75.00, 51);
            assertFalse(processor.isValidOrder(order));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -10})
        @DisplayName("Order with zero or negative item count fails validation")
        void testInvalidItemCounts(int itemCount) {
            Order order = new Order("CUST-003", 75.00, itemCount);
            assertFalse(processor.isValidOrder(order));
        }
    } // End of Order Validation Tests

    @Test
    @DisplayName("Full total calculation reflects subtotal minus applicable discount")
    void testCalculateTotalWithDiscount() {
        Order order = new Order("CUST-004", 200.00, 5);

        double total = processor.calculateTotal(order);

        /*
          * The assertAll() method groups multiple assertions together
          * if one fails,JUnit still runs and reports the others, rather
          * than stopping at the first failure (unlike separate,
          * sequential assertions)
         */
        assertAll("total calculation",
                () -> assertEquals(180.00, total, 0.001),
                () -> assertTrue(processor.isValidOrder(order)),
                () -> assertEquals(20.00, processor.calculateDiscount(order.getSubtotal()), 0.001)
        );
    }

    @Test
    @DisplayName("Negative subtotal throws IllegalArgumentException")
    void testNegativeSubtotalThrowsException() {
        Order invalidOrder = new Order("CUST-005", -50.00, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            processor.calculateTotal(invalidOrder);
        });
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("OrderProcessor test suite complete.");
    }
}
