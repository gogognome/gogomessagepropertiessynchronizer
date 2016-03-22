package nl.gogognome.messagepropertiessynchronizer;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

import static java.util.stream.Collectors.joining;

public class Start {

    public static void main(String args[]) {
        Options options = new Options();
        options.addOption("t", "todo", true, "todo message to be inserted in added lines");
        options.addOption("h", "help", false, "prints this message");

        options.addOption(Option.builder("s").argName("source file").hasArg().desc("source message properties").longOpt("source").required().build());
        options.addOption(Option.builder("d").argName("destination file").hasArg().desc("destination message properties").longOpt("destination").required().build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (!commandLine.getArgList().isEmpty()) {
                throw new ParseException("unknown argument(s) found: " + commandLine.getArgList().stream().collect(joining(", ")));
            }
            if (commandLine.hasOption("h")) {
                printHelpMessage(options);
            }

            File source = new File(commandLine.getOptionValue('s'));
            File destination = new File(commandLine.getOptionValue('d'));
            String todoMessage = commandLine.getOptionValue('t', "<TODO TRANSLATE>");
            try {
                System.out.println("Synchronizing " + source.getAbsolutePath() + " with " + destination.getAbsolutePath() + "...");
                new FileSynchronizer(todoMessage).synchronize(source, destination);
                System.out.println("Done!");
            } catch (IOException e) {
                System.err.println("A problem occurred while synchronizing " + source.getAbsolutePath() + " and " + destination.getAbsolutePath() + ": " + e.getMessage());
            }
        } catch(ParseException exp) {
            System.err.println( "Invalid command line arguments: " + exp.getMessage() );
            printHelpMessage(options);
        }
    }

    private static void printHelpMessage(Options options) {
        new HelpFormatter().printHelp( "java -jar gogomessagepropertiessynchronizer-1.0.jar", options, true);
    }
}
