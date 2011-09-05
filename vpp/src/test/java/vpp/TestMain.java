/*
 * TestMain.java
 * By: Denver Coneybeare
 * 2011-09-05
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TestMain {

    @Test
    public void testConstructorArrayLength1() {
        final Main x = new Main(new String[] { "hello" });
        final String[] actual = x.getArgs();
        final String[] expected = new String[] { "hello" };
        assertArrayEquals("new Main(args).getArgs()", expected, actual);
    }

    @Test
    public void testConstructorArrayLength1NullElement() {
        final Main x = new Main(new String[] { null });
        final String[] actual = x.getArgs();
        final String[] expected = new String[] { null };
        assertArrayEquals("new Main(args).getArgs()", expected, actual);
    }

    @Test
    public void testConstructorArrayLengthGreaterThan1() {
        final Main x = new Main(new String[] { "one", "two", null });
        final String[] actual = x.getArgs();
        final String[] expected = new String[] { "one", "two", null };
        assertArrayEquals("new Main(args).getArgs()", expected, actual);
    }

    @Test
    public void testConstructorEmptyArray() {
        final Main x = new Main(new String[0]);
        final String[] actual = x.getArgs();
        final String[] expected = new String[0];
        assertArrayEquals("new Main(args).getArgs()", expected, actual);
    }

    @Test
    public void testConstructorNull() {
        final Main x = new Main(null);
        final String[] actual = x.getArgs();
        assertNull("new Main(null).getArgs()", actual);
    }

    @Test
    public void testConstructorRef() {
        final String[] expected = new String[0];
        final Main x = new Main(expected);
        final String[] actual = x.getArgs();
        assertSame("new Main(args).getArgs()", expected, actual);
    }

}
