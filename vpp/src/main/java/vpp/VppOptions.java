/*
 * VppOptions.java
 * By: Denver Coneybeare
 * Sept 20, 2011
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Velocity Pre-processor options.
 * <p>
 * Every method of this class is declared as "synchronized"; therefore,
 * instances of this class may be safely accessed concurrently from multiple
 * threads. To perform multiple operations atomically, simply synchronize on the
 * instance of this class.
 */
public class VppOptions {

    private final List<String> inputPaths;
    private String outputPath;
    private final Map<String, String> defines;

    /**
     * Creates a new instance of <code>VppOptions</code>.
     */
    public VppOptions() {
        this.inputPaths = new ArrayList<String>();
        this.defines = new HashMap<String, String>();
    }

    /**
     * Adds a path to this object's list of input paths. No checking for
     * duplicates is added; therefore, if the given path is already in the list
     * then it is added again.
     * 
     * @param path the path to add
     * @throws NullPointerException if path==null
     * @see #getInputPaths()
     * @see #getNumInputPaths()
     * @see #removeInputPath(String)
     */
    public synchronized void addInputPath(String path) {
        if (path == null) {
            throw new NullPointerException("path==null");
        }
        this.inputPaths.add(path);
    }

    /**
     * Returns the value of a define set in this object.
     * 
     * @param key the key of the define whose value to return; may be null
     * @return a string whose value is the value of the given define; returns
     * null if the define is not set, if the define is explicitly set to null,
     * or if the given key is null
     * @see #setDefine(String, String)
     * @see #removeDefine(String)
     */
    public synchronized String getDefine(String key) {
        if (key == null) {
            return null;
        }
        final String value = this.defines.get(key);
        return value;
    }

    /**
     * Returns the keys of this object's set of defines. This method creates a
     * new array and copies each of the keys into that array and returns it. The
     * keys will be unordered.
     * 
     * @return a newly-created array whose values are the keys of the defines,
     * unordered; never returns null and no elements of the array will be null
     * @see #setDefine(String, String)
     * @see #removeDefine(String)
     */
    public synchronized String[] getDefineKeys() {
        final String[] array = new String[this.defines.size()];
        this.defines.keySet().toArray(array);
        return array;
    }

    /**
     * Returns this object's list of input paths. This method creates a new
     * array and copies each of the input paths into that array and returns it.
     * The paths will be in the same order as they were added.
     * <p>
     * If the returned list is empty, then it indicates that input is to be read
     * from standard error instead of from any particular file in the
     * filesystem.
     * 
     * @return a newly-created array whose values are the input paths in the
     * same order in which they were added; never returns null and no elements
     * of the array will be null
     * @see #addInputPath(String)
     * @see #getNumInputPaths()
     * @see #removeInputPath(String)
     */
    public synchronized String[] getInputPaths() {
        final String[] array = new String[this.inputPaths.size()];
        this.inputPaths.toArray(array);
        return array;
    }

    /**
     * Returns the number of elements in this object's list of input paths.
     * <p>
     * If the returned values is zero, then it indicates that input is to be
     * read from standard error instead of from any particular file in the
     * filesystem.
     * 
     * @return the number of paths in this object's list of input paths
     * @see #addInputPath(String)
     * @see #getInputPaths()
     * @see #removeInputPath(String)
     */
    public synchronized int getNumInputPaths() {
        final int size = this.inputPaths.size();
        return size;
    }

    /**
     * Returns the output path to use.
     * 
     * @return the output path to use; may be null, which indicates that output
     * should be written to standard output instead of any particular file
     * @see #setOutputPath(String)
     */
    public synchronized String getOutputPath() {
        return this.outputPath;
    }

    /**
     * Removes a define from this object's set of defines. If the given define
     * is not present in this object's set of defines then this method does
     * nothing and returns as if successful.
     * 
     * @param key the key of the define to remove; may be null, in which case
     * this method behaves as if the given define was not present in the set
     * @see #setDefine(String, String)
     * @see #getDefineKeys()
     */
    public synchronized void removeDefine(String key) {
        if (key != null) {
            this.defines.remove(key);
        }
    }

    /**
     * Removes a path from this object's list of input paths. If the given path
     * is not present in this object's list of input paths then this method does
     * nothing and returns as if successful. If the given path is present
     * multiple times in this object's list of input paths then only one of them
     * is removed; there is no guarantee of which occurrence will actually be
     * removed.
     * 
     * @param path the path to remove; may be null, in which case this method
     * behaves as if the given path was not present in the list
     * @see #addInputPath(String)
     * @see #getInputPaths()
     * @see #getNumInputPaths()
     */
    public synchronized void removeInputPath(String path) {
        if (path != null) {
            this.inputPaths.remove(path);
        }
    }

    /**
     * Sets a define in this object. If a define with the given key is already
     * set then it is replaced with the new value.
     * 
     * @param key the key of the define to add
     * @param value the key of the define to add; may be null
     * @throws NullPointerException if key==null
     * @see #getDefineKeys()
     * @see #removeDefine(String)
     */
    public synchronized void setDefine(String key, String value) {
        if (key == null) {
            throw new NullPointerException("key==null");
        }
        this.defines.put(key, value);
    }

    /**
     * Sets the output path to use.
     * 
     * @param path the output path to use; may be null, which indicates that
     * output should be written to standard output instead of any particular
     * file
     * @see #getOutputPath()
     */
    public synchronized void setOutputPath(String path) {
        this.outputPath = path;
    }

}
