package commands.impl;

import java.io.InputStream;
import java.io.PrintStream;

import commands.ICommand;

public class ExternalCommand implements ICommand {

    private final String command;

    public ExternalCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(String[] args) {

        
        try {
            // Replace command name with absolute executable path
            args[0] = command; 
            
            ProcessBuilder pb = new ProcessBuilder(args);

            // show output to the parent process's stream
            // pb.inheritIO();  

            Process process = pb.start();

              // PIPE stdout to System.out
            try (InputStream out = process.getInputStream()) {
                out.transferTo(System.out);
            }

            // PIPE stderr to System.err
            try (InputStream err = process.getErrorStream()) {
                err.transferTo(System.err);
            }

            process.waitFor();
        }
        catch(Exception e) {

        }
       
    }

}
