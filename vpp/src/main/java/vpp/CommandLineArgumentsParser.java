/*
 * CommandLineArgumentsParser.java
 * By: Denver Coneybeare
 * Sept 05, 2011
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp;

import static vpp.util.Arrays.copyFailIfNull;

/**
 * Parses the command-line arguments for the Velocity Preprocessor application.
 */
public class CommandLineArgumentsParser {

    private final String[] args;

    /**
     * Creates a new instance of <code>CommandLineArgumentsParser</code>.
     * <p>
     * A copy of the given String array will be stored internally and no
     * references to the given array will be stored internally.
     * 
     * @param args the command-line arguments to use
     * @throws NullPointerException if <code>args==null</code> or any element of
     * <code>args</code> is null
     */
    public CommandLineArgumentsParser(String[] args) {
        this.args = copyFailIfNull(args, "args");
    }

    /**
     * Returns the command-line arguments array used by this class. The returned
     * array is a copy, and may be freely modified by the caller without any
     * effect whatsoever to this object.
     * 
     * @return the command-line arguments array that was specified to the
     * constructor; never returns null and the array will never contain null
     * elements
     */
    public String[] getArgs() {
        return this.args;
    }
}
