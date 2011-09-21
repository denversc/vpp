/*
 * Vpp.java
 * By: Denver Coneybeare
 * Sept 20, 2011
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp;

/**
 * The Velocity pre-processor.
 */
public class Vpp {

    private final VppOptions options;

    /**
     * Creates a new instance of <code>Vpp</code>.
     * 
     * @param options the options for this object
     * @throws NullPointerException if options==null
     */
    public Vpp(VppOptions options) {
        if (options == null) {
            throw new NullPointerException("options==null");
        }
        this.options = options;
    }

    /**
     * Returns this object's options.
     * 
     * @return the object that was given to the constructor for the "options"
     * parameter; never returns null
     */
    public VppOptions getOptions() {
        return this.options;
    }
}
