package org.digitinary.taskManagement.entities;

import org.digitinary.taskManagement.Exceptions.InvalidEmailException;
import org.digitinary.taskManagement.Exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
        User.usersList.clear();  // Reset the shared usersList before each test
    }


    /**
     * Test to verify that an invalid email is correctly identified.
     */
    @Test
    void emailShouldBeInvalid()  {
        User user = new User("123", "majd");
        assertFalse(user.validateEmail("jalal"));
    }
    /**
     * Test to verify that an invalid id is correctly identified.
     */
    @Test
    void idShouldBeInvalid()  {
        User u = new User("123", "majd");
        u.addUser();
        assertFalse(u.validateId("123"));
    }
    /**
     * Test to verify that an exception is thrown when creating a user with an invalid id for first constructor .
     * This test expects an exception because the id "123" is not valid.
     */
    @Test
    void shouldThrowExceptionForInvalidIdForFirstConstructor() {
        User u = new User("123", "fadi");
        u.addUser();
        assertThrows(InvalidIdException.class, () -> {
            new User("123", "jalal");
        });
    }
    /**
     * Test to verify that an exception is thrown when creating a user with an invalid id, for the sec constructor
     * This test expects an exception because the id "123" is not valid.
     */
    @Test
    void shouldThrowExceptionForInvalidIdForSecConstructor() {
        User u = new User("123", "fadi");
        u.addUser();
        assertThrows(InvalidIdException.class, () -> {
            new User("123", "jalal", "ja@gmail.org");
        });
    }

    /**
     * Test to verify that a valid email is correctly identified.
     */
    @Test
    void emailShouldBeValid()  {
        User user = new User("123", "majd");
        assertTrue(user.validateEmail("jalal@gmail.com"));
    }
    /**
     * Test to verify that an exception is thrown when creating a user with an invalid email.
     * This test expects an exception because the email "jalal" is not valid.
     */
    @Test
    void shouldThrowExceptionForInvalidEmail() {
        assertThrows(InvalidEmailException.class, () -> {
            new User("123", "jalal", "jalal");
        });
    }
    /**
     * Test to verify that no exception is thrown when creating a user with a valid email.
     * This test ensures that the valid email "jalal@gmail.com" does not cause an exception.
     */
    @Test
    void shouldNotThrowExceptionForInvalidEmail() {
        assertDoesNotThrow(() -> {
            new User("123", "jalal", "jalal@gmail.com");
        });
    }
    /**
     * Test to verify that adding two users updates the usersList size correctly.
     * The usersList size should increase by 2 after adding two users.
     */
    @Test
    void lengthOfUsersListShouldGetUpdatedByTwo(){
        int len = User.usersList.size();
        User user = new User("123", "majd");
        User user2 = new User("1234", "majd");
        assertTrue(user.addUser());
        assertTrue(user2.addUser());
        assertEquals(len + 2, User.usersList.size());
    }
    /**
     * Test to verify that removing a user decrements the usersList size correctly.
     * The usersList size should decrease by 1 after removing one user.
     */
    @Test
    void lengthOfUsersListShouldGetDecrementedByOne() {
        int len = User.usersList.size();
        User user = new User("123", "majd");
        User user2 = new User("1234", "majd");
        user.addUser();
        user2.addUser();
        user.removeUser();
        assertEquals(len + 1, User.usersList.size());
    }
    /**
     * Test to verify that updating a user's name and email works correctly.
     * The user's name and email should be updated in the usersList.
     *
     * @throws Exception if an error occurs during user update
     */
    @Test
    void nameAndEmailShouldBeUpdated() throws Exception {
        User initial = new User("123", "majd", "mjd@gmail.com");
        initial.addUser();
        initial.updateUser("majdjalal", "mjd@gmail.org");
        assertEquals("majdjalal", User.usersList.get(initial.getId()).getName());
        assertEquals("mjd@gmail.org", User.usersList.get(initial.getId()).getEmail());
    }


}