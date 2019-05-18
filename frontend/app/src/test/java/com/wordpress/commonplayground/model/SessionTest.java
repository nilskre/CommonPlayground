package com.wordpress.commonplayground.model;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SessionTest {
    private static Session testSession;


    @BeforeClass
    public static void setUp() {
        testSession = new Session("Title", "Description", "Game", "Place", "12.12.2100", "12:00", 42, (long) 4, "Genre", "Online", new ArrayList<>(), (long) 4, new ArrayList<>());
    }

    @Test
    public void testName() {
        assertEquals(testSession.getTitle(), "Title");
    }

    @Test
    public void testDescription() {
        assertEquals(testSession.getDescription(), "Description");
    }

    @Test
    public void testGame() {
        assertEquals(testSession.getGame(), "Game");
    }

    @Test
    public void testPlace() {
        assertEquals(testSession.getPlace(), "Place");
    }

    @Test
    public void testDate() {
        assertEquals(testSession.getDate(), "12.12.2100");
    }

    @Test
    public void testTime() {
        assertEquals(testSession.getTime(), "12:00");
    }

    @Test
    public void testNumberOfPlayers() {
        assertEquals(testSession.getNumberOfPlayers(), 42);
    }
}