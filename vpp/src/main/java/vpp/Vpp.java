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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

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

    private File[] getInputFiles() throws VppException {
        final String[] paths = this.options.getInputPaths();
        final File[] files;
        if (paths == null || paths.length == 0) {
            files = new File[1];
        } else {
            files = new File[paths.length];
            for (int i = 0; i < paths.length; i++) {
                final String path = paths[i];
                if (path != null) {
                    final File file = new File(path);
                    if (!file.exists()) {
                        throw new VppException("File not found: " + path);
                    } else if (!file.isFile()) {
                        throw new VppException("Not a file: " + path);
                    }
                    files[i] = file;
                }
            }
        }
        return files;
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

    private File getOutputFile() throws VppException {
        final String path = this.options.getOutputPath();
        final File file;
        if (path == null) {
            file = null;
        } else {
            file = new File(path);
            if (file.isDirectory()) {
                throw new VppException("output path is not a file: " + path);
            }
        }

        return file;
    }

    /**
     * Runs the Velocity Pre-processor with the options that were specified to
     * the constructor.
     * 
     * @throws VppException if an error occurs
     */
    public void run() throws VppException {
        final File[] inFiles = this.getInputFiles();

        final Context context = new VelocityContext();
        synchronized (this.options) {
            final String[] defineKeys = this.options.getDefineKeys();
            for (final String defineKey : defineKeys) {
                final String defineValue = this.options.getDefine(defineKey);
                context.put(defineKey, defineValue);
            }
        }
        final VelocityEngine velocity = new VelocityEngine();

        final File outFile = this.getOutputFile();
        final Writer writer;
        final String writerName;
        final boolean closeWriter;
        if (outFile == null) {
            writer = new OutputStreamWriter(System.out);
            writerName = "<standard output>";
            closeWriter = false;
        } else {
            writerName = outFile.getPath();
            closeWriter = true;
            try {
                writer = new FileWriter(outFile);
            } catch (final IOException e) {
                throw new VppException("unable to open file for writing: "
                    + writerName + " (" + e.getMessage() + ")");
            }
        }

        try {
            for (final File inFile : inFiles) {
                final Reader reader;
                final String readerName;
                final boolean closeReader;
                if (inFile == null) {
                    reader = new InputStreamReader(System.in);
                    readerName = "<standard input>";
                    closeReader = false;
                } else {
                    readerName = inFile.getPath();
                    closeReader = true;
                    try {
                        reader = new FileReader(inFile);
                    } catch (final IOException e) {
                        throw new VppException(
                            "unable to open file for reading: " + readerName
                                + " (" + e.getMessage() + ")");
                    }
                }

                try {
                    velocity.evaluate(context, writer, "vpp", reader);
                } finally {
                    if (closeReader) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            throw new VppException(
                                "unable to close input file: " + readerName
                                    + " (" + e.getMessage() + ")");
                        }
                    }
                }
            }
        } finally {
            try {
                if (closeWriter) {
                    writer.close();
                } else {
                    writer.flush();
                }
            } catch (final IOException e) {
                throw new VppException("unable to close output file: "
                    + writerName + " (" + e.getMessage() + ")");
            }
        }
    }
}
