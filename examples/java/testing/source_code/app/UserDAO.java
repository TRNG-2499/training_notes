package com.revature.junit.app;

/*
 * The UserDAO will be mocked (notice that we do not
 * have any implementation for the UserDAO - this is
 * to showcase how mocking is used to control the test
 * environment, without any connection to a database (or
 * any other system) whatsoever.
 *
 * Note: when performing integration tests - we would
 * need an implementation of this interface
 */
public interface UserDAO {
    User findByUsername(String username);

    // Note: This method would be used to record failed login
    // attempts to our database - hence it's existence in UserDAO
    void recordFailedLoginAttempt(String username);
}


class MockedClass implements UserDAO {
    public User findByUsername(String username) { return null; }
    public void recordFailedLoginAttempt(String username) { }
}
