package app;

// Our Application code imports
import com.revature.junit.app.User;
import com.revature.junit.app.UserDAO;
import com.revature.junit.app.LoginResult;
import com.revature.junit.app.LoginValidator;

// JUnit Annotation Imports
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// Mockito Annotation & class imports
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Static Assertion Method imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;

// This @ExtendWith annotation is a modern approach for
// injecting mocks - it will automatically inject the appropriate
// mocked objects into your Classes as needed (i.e. no '@InjectMocks')
// annotations required!
@ExtendWith(MockitoExtension.class)
public class LoginValidatorTest {

    // The dependency being mocked -- no real database connection involved
    @Mock
    private UserDAO userDAO;

    // The class under test -- built manually here (rather than with
    // @InjectMocks) to keep the wiring explicit and visible for this example
    private LoginValidator loginValidator;

    @BeforeEach
    void setUp() {
        loginValidator = new LoginValidator(userDAO);
    }

    @Test
    @DisplayName("Valid username and password returns SUCCESS")
    void testValidLogin() {
        // Arrange: Create a mock user. This is dummy data that we will used
        // to ensure that our code is functioning correctly
        // We also prepare the method stub (see 'when(userDAO.findByUsername("jdoe")).thenReturn(mockUser);'
        User mockUser = new User("jdoe", "correct-password", false);

        // Stubbing: tell the mock what to return when this specific
        // method is called with this specific argument
        when(userDAO.findByUsername("jdoe")).thenReturn(mockUser);

        // Act
        // When calling the loginValidator's 'validateLogin' method, the
        // mocked userDAO will return our controlled data (the 'mockUser')
        LoginResult result = loginValidator.validateLogin("jsmith", "correct-password");

        // Assert: Did the output match?
        // ALSO - lets verify that functionality of our method
        // i.e.... Was the appropriate methods called?
        // were unappropriate methods NOT called?
        assertEquals(LoginResult.SUCCESS, result);

        // verify() confirms a specific method WAS called on the mock --
        // useful for confirming side effects, not just return values
        verify(userDAO, times(1)).findByUsername("jdoe");

        // A 'failed attempt' should NEVER be recorded on a successful login
        verify(userDAO, never()).recordFailedLoginAttempt(anyString());
    }

    @Test
    @DisplayName("Unknown username returns USER_NOT_FOUND")
    void testUserNotFound() {
        // No stubbing needed for a "not found" case -- an unstubbed mock
        // method returns null by default for object return types
        when(userDAO.findByUsername("ghost")).thenReturn(null);

        LoginResult result = loginValidator.validateLogin("ghost", "any-password");

        assertEquals(LoginResult.USER_NOT_FOUND, result);

        // Since the user was never found, no failed-attempt recording
        // should have been triggered
        verify(userDAO, never()).recordFailedLoginAttempt(anyString());
    }

    @Test
    @DisplayName("Locked account returns ACCOUNT_LOCKED, regardless of password")
    void testAccountLocked() {
        User lockedUser = new User("jdoe", "correct-password", true);
        when(userDAO.findByUsername("jdoe")).thenReturn(lockedUser);

        LoginResult result = loginValidator.validateLogin("jdoe", "correct-password");

        assertEquals(LoginResult.ACCOUNT_LOCKED, result);
    }

    @Test
    @DisplayName("Incorrect password returns INVALID_PASSWORD and records the failed attempt")
    void testInvalidPassword() {
        User mockUser = new User("jdoe", "correct-password", false);
        when(userDAO.findByUsername("jdoe")).thenReturn(mockUser);

        LoginResult result = loginValidator.validateLogin("jdoe", "wrong-password");

        assertEquals(LoginResult.INVALID_PASSWORD, result);

        // Confirm the side effect actually happened -- this is something
        // a plain return-value check alone would never catch
        verify(userDAO, times(1)).recordFailedLoginAttempt("jdoe");
    }

    @Test
    @DisplayName("findByUsername is called with the exact username provided, not a hardcoded one")
    void testCorrectUsernamePassedToDAO() {
        User mockUser = new User("specific-user", "pw", false);
        when(userDAO.findByUsername("specific-user")).thenReturn(mockUser);

        loginValidator.validateLogin("specific-user", "pw");

        // verify() can also confirm the EXACT argument a method was called with --
        // catches bugs like the hardcoded values
        verify(userDAO).findByUsername("specific-user");
        verify(userDAO, never()).findByUsername("some-other-user");
    }
}
