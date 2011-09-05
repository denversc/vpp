/*
 * Main.java
 * By: Denver Coneybeare
 * Sept 05, 2011
 *
 * Copyright 2011 Denver Coneybeare
 * This file is released under the Apache License Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package vpp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The main entry point for the Velocity Preprocessor.
 */
public class Main implements Runnable {

    private final String[] args;

    /**
     * Creates a new instance of <code>Main</code>.
     * <p>
     * A reference to the given String array will be stored internally.
     * Therefore, no modifications to this array should be done; otherwise,
     * undefined behaviour may occur.
     * 
     * @param args the command-line arguments to use; may be null
     */
    public Main(String[] args) {
        this.args = args;
    }

    /**
     * Exits the Java Virtual Machine with the given status. This method never
     * returns.
     * <p>
     * The implementation of this method in this class simply invokes
     * {@link System#exit(int)} with the given status. It is invoked by
     * {@link #run()} to terminate the Java Virtual Machine. It may be
     * overridden to terminate the JVM in some non-standard way, which will
     * especially be useful if unit testing this class. Normally, there is no
     * reason to override this method.
     * 
     * @param status the exit status; conventionally, 0 (zero) indicates
     * success, 1 (one) indicates abnormal program termination, and 2 (two)
     * indicates an error processing the command-line arguments occurred.
     */
    public void exit(int status) {
        System.exit(status);
    }

    /**
     * Returns the command-line arguments array used by this class. A reference
     * to the exact array that was specified to the constructor is returned;
     * therefore, it is strongly recommended to <em>not</em> modify the array in
     * any way, as it may lead to undefined behaviour.
     * 
     * @return the command-line arguments array that was specified to the
     * constructor; may be null
     */
    public String[] getArgs() {
        return this.args;
    }

    private void parseArgs() throws ParseException {
        final Option outputPathOption =
            new Option("o", "output-path", true,
                "The output file or directory. If not specified then the "
                    + "output is written to standard output.");

        final Option helpOption =
            new Option("h", "help", false,
                "Print help for this application and exit");

        final Options options = new Options();
        options.addOption(outputPathOption);
        options.addOption(helpOption);

        final GnuParser parser = new GnuParser();
        final CommandLine parsedArgs = parser.parse(options, this.args);
        final String[] leftoverArgs = parsedArgs.getArgs();

        boolean printHelp = false;
        final Option[] parsedOptions = parsedArgs.getOptions();
        for (final Option option : parsedOptions) {
            final String value = option.getValue();
            System.out.println(option.getOpt() + "=" + value);

            if (option.getOpt().equals("h")) {
                printHelp = true;
            }
        }

        System.out.println("Leftover Args (" + leftoverArgs.length + ")");
        for (int i = 0; i < leftoverArgs.length; i++) {
            System.out.println("  " + i + ": " + leftoverArgs[i]);
        }

        if (printHelp) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("[options] [input paths]", options);
        }
    }

    /**
     * Runs the Velocity Preprocessor using the command-line arguments that were
     * given to the constructor. Upon completion, {@link #exit(int)} is invoked
     * to terminate the Java Virtual Machine; therefore, under normal
     * circumstances this method never returns.
     */
    public void run() {
        int exitCode = 0;
        try {
            this.parseArgs();
        } catch (final ParseException e) {
            System.err.println("ERROR: " + e.getMessage());
            exitCode = 2;
        }

        this.exit(exitCode);
    }

    /**
     * The main entry point for the Velocity Preprocessor application. This
     * method simply creates a new instance of this class with the given String
     * array and then invokes {@link #run()}.
     * 
     * @param args the command-line arguments; may be null.
     */
    public static void main(String[] args) {
        final Main main = new Main(args);
        main.run();
    }

}
