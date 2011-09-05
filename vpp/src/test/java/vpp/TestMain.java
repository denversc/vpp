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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TestMain {

    @Test(expected = AssertionError.class)
    public void test_MainExitMocked_assertExitInvokedOnce_0() {
        final MainExitMocked x = new MainExitMocked(null);
        x.assertExitInvokedOnce(5);
    }

    @Test(expected = AssertionError.class)
    public void test_MainExitMocked_assertExitInvokedOnce_2() {
        final MainExitMocked x = new MainExitMocked(null);
        x.exit(5);
        x.exit(5);
        x.assertExitInvokedOnce(5);
    }

    @Test
    public void test_MainExitMocked_assertExitInvokedOnce_ExpectedStatus() {
        final MainExitMocked x = new MainExitMocked(null);
        x.exit(5);
        x.assertExitInvokedOnce(5);
    }

    @Test(expected = AssertionError.class)
    public void test_MainExitMocked_assertExitInvokedOnce_UnexpectedStatus() {
        final MainExitMocked x = new MainExitMocked(null);
        x.exit(0);
        x.assertExitInvokedOnce(5);
    }

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

    @Test
    public void testRun() {
        final MainExitMocked x = new MainExitMocked(null);
        x.run();
        x.assertExitInvokedOnce(0);
    }

    /**
     * A subclass of Main which overrides exit() to simply record the invocation
     * and not call System.exit().
     */
    private static class MainExitMocked extends Main {

        private final Object exitLock;
        private int exitStatus;
        private int exitCount;

        public MainExitMocked(String[] args) {
            super(args);
            this.exitLock = new Object();
        }

        public void assertExitInvokedOnce(int expectedStatus) {
            assertEquals("exit() invocation count", 1, this.exitCount);
            assertEquals("exit() status", expectedStatus, this.exitStatus);
        }

        public void exit(int status) {
            synchronized (this.exitLock) {
                this.exitStatus = status;
                this.exitCount++;
            }
        }
    }
}
