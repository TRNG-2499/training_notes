package mocking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.junit.mocking.AppData;
import com.revature.junit.mocking.SetterInjection;
import com.revature.junit.mocking.FieldInjection;
import com.revature.junit.mocking.ConstructorInjection;

/*
 * When injecting data, the following will occur:
 *  1 - Mockito creates the mock data (@Mock)
 *  2 - Mockito creates the objects that need that mock data injected (@InjectMocks)
 *  3 - Field | Constructor | Setter Injection occurs automatically
 *
 * @ExtendWith(MockitoExtension.class) tells JUnit 5 to let Mockito
 * initialize all @Mock/@InjectMocks fields automatically before each
 * test - No manual setup required.
 *
 * NOTE: Without this annotation, you would need a setup method that calls
 * the 'openMocks' method:
 * @BeforeEach
 * public void setUp() {
 *      // Import MockitoAnnotations from: 'org.mockito.MockitoAnnotations'
 *      MockitoAnnotations.openMocks(this);
 * }
 */
@ExtendWith(MockitoExtension.class) // This annotation replaces the need to call MockitoAnnotations.openMocks(this)
public class InjectionTests {

    // We will inject mock data into this object
    @InjectMocks
    ConstructorInjection conInject;

    // We will inject mock data into this object
    @InjectMocks
    SetterInjection setInject;

    // We will inject mock data into this object
    @InjectMocks
    FieldInjection fieldInject;

    // This is the data we are mocking
    @Mock
    AppData data;

    @Test
    @DisplayName("Constructor injection: performTask(100) returns true")
    void constructorInjectionTest_1() {
        /*
         * Mockito 'when' method controls the data returned from a mocked
         * object when called with given arguments -> perform tests in
         * a controlled way
         */
        Mockito.when(
                data.performTask(150)
        ).thenReturn(true);

        int testValue = 100;
        boolean result = conInject.execute(testValue);
        assertTrue(result);
    }

    @Test
    @DisplayName("Constructor injection: performTask(1) returns false")
    void constructorInjectionTest_2() {
        /*
         * The mocked AppData object will be called with
         * the number 1
         *
         * When that happens, return the boolean false
         */
        Mockito.when(
                data.performTask(51)
        ).thenReturn(false);

        /*
         * This method is now Adding 50 to the number given
         * BEFORE i call the 'performTask' method on my AppData
         * reference variable (remember I am MOCKING that AppData
         * for my tests)
         */
        boolean result = conInject.execute(1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Setter injection: performTask(100) returns true")
    void setterInjectionTest_1() {
        Mockito.when(data.performTask(100)).thenReturn(true);

        boolean result = setInject.execute(100);
        assertTrue(result);
    }

    @Test
    @DisplayName("Setter injection: performTask(1) returns false")
    void setterInjectionTest_2() {
        Mockito.when(data.performTask(1)).thenReturn(false);

        boolean result = setInject.execute(1);
        assertFalse(result);
    }

    @Test
    @DisplayName("Field injection: performTask(100) returns true")
    void fieldInjectionTest_1() {
        Mockito.when(data.performTask(100)).thenReturn(true);

        boolean result = fieldInject.execute(100);
        assertTrue(result);
    }

    @Test
    @DisplayName("Field injection: performTask(1) returns false")
    void fieldInjectionTest_2() {
        Mockito.when(data.performTask(1)).thenReturn(false);

        boolean result = fieldInject.execute(1);
        assertFalse(result);
    }
}
