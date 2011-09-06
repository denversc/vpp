/*
 * Arrays.java
 * By: Denver Coneybeare
 * 2011-09-05
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp.util;

/**
 * Utility functions for working with arrays.
 */
public class Arrays {

    /**
     * Private constructor to prevent instantiation.
     */
    private Arrays() {
    }

    /**
     * Creates a copy of a string array. A new array will be created with the
     * same length as the given array and then all elements from the given array
     * will be copied into it. Therefore, the returned array will contain the
     * same elements in the same order but the array itself will be distinct
     * from the given array.
     * 
     * @param array the array to copy; may be null
     * @return a copy of the given array; returns null if and only if the given
     * array is null
     */
    public static String[] copy(String[] array) {
        if (array == null) {
            return null;
        }
        final String[] copy = new String[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    /**
     * Creates a copy of a string array, failing if the array reference or any
     * of the elements in the referenced array are null. The semantics of this
     * method are identical to {@link #copy(String[])} except for the
     * possibility of a <code>NullPointerException</code>, but also this method
     * is less efficient. This method was created to be used by methods that
     * take an array as a parameter and want to store a copy internally,
     * throwing <code>NullPointerException</code> if any nulls are encountered.
     * <p>
     * If a <code>NullPointerException</code> is thrown, then its message will
     * be: <code>arrayName+"==null"</code>.
     * 
     * @param array the array to copy
     * @param arrayName the "name" of the array to use in the message of a
     * <code>NullPointerException</code>, if thrown
     * @return a newly-created array that is the same length as the given array
     * with the exact same elements in the exact same order; never returns null
     * @throws NullPointerException if <code>array==null</code> or if any
     * element of <code>array</code> is null
     */
    public static String[] copyFailIfNull(String[] array, String arrayName) {
        if (array == null) {
            throw new NullPointerException(arrayName + "==null");
        }

        final String[] copy = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            final String element = array[i];
            if (element == null) {
                throw new NullPointerException(arrayName + "[" + i + "]==null");
            }
            copy[i] = element;
        }

        return copy;
    }
}
