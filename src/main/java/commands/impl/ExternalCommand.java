package commands.impl;

import java.io.*;
import java.util.Arrays;

import commands.ICommand;

import static java.nio.file.Files.readAllBytes;

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
            // process.getInputStream() = output stream of process or command
            try (InputStream out = process.getInputStream()) {
                out.transferTo(System.out);
            }

            // PIPE stderr to System.err
            // process.getErrorStream() = stderr of the external command
            try (InputStream err = process.getErrorStream()) {
                err.transferTo(System.err);
            }

            process.waitFor();
        }
        catch(Exception e) {

        }
       
    }

}
