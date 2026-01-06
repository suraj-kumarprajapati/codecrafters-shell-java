package commands.impl;

import java.io.PrintStream;

import commands.ICommand;

public class ExternalCommand implements ICommand {

    private final String command;

    public ExternalCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(String[] args) {

        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        
        try {
            // Replace command name with absolute executable path
            args[0] = command; 
            
            ProcessBuilder pb = new ProcessBuilder(args);

            // show output to the parent process's stream
            // pb.inheritIO();  

            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = pb.start();
            process.waitFor();
        }
        catch(Exception e) {

        }
        finally {
            // Restore Java streams 
            System.setOut(originalOut);
            System.setErr(originalErr);
        }
    }

}
