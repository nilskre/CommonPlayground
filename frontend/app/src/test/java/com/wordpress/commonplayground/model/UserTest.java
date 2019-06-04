package com.wordpress.commonplayground.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private static User testUser;

    @BeforeClass
    public static void setUp() {
        testUser = new User((long) 4, "Name", "test@test.de");
    }

    @Test
    public void testId() {
        assertEquals(0, Long.compare(testUser.getId(), 4));
    }

    @Test
    public void testUserName() {
        assertEquals(testUser.getName(), "Name");
    }

    @Test
    public void testEmail() {
        assertEquals(testUser.getEmail(), "test@test.de");
    }
}