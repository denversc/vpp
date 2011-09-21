/*
 * VppException.java
 * By: Denver Coneybeare
 * Sept 20, 2011
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp;

/**
 * Exception thrown by the Velocity Pre-processor.
 */
public class VppException extends Exception {

    /**
     * Creates a new instance of <code>VppException</code>.
     */
    public VppException() {
        super();
    }

    /**
     * Creates a new instance of <code>VppException</code>.
     * 
     * @param message the message for the exception; may be null
     */
    public VppException(String message) {
        super(message);
    }
}
