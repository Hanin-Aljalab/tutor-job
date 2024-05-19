package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Jessica", "Steinberger", "password", "Prof. Dr.", "Teacher");
    }

    @Test
    void testGetFirstName() {
        assertEquals("Jessica", user.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Steinberger", user.getLastName());
    }

    @Test
    void testGetTitle() {
        assertEquals("Prof. Dr.", user.getTitle());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void testGetRole() {
        assertEquals("Teacher", user.getRole());
    }
}