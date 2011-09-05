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
}
