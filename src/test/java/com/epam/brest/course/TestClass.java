package com.epam.brest.course;

import org.junit.Test;
import org.junit.runner.Result;

import static junit.framework.Assert.assertEquals;


public class TestClass {

    /**
     * Test for ArrayIndexOutOfBoundsException
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetArrayElement(){
        App.getArrayElement(-1);
        App.getArrayElement(121);
    }

    /**
     * Tests the method int findSum(int, int)
     */
    @Test
    public void testFindSum(){
        assertEquals(8, App.findSum(3,5));
        assertEquals(-14, App.findSum(3, -17));
        assertEquals(12, App.findSum(-5, 17));
        assertEquals(Integer.MIN_VALUE,App.findSum(Integer.MAX_VALUE, 1));
    }
}
