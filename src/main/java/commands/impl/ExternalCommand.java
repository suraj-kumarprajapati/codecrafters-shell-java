package commands.impl;

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
            // show output to the shell
            pb.inheritIO();  

            Process process = pb.start();
            process.waitFor();
        }
        catch(Exception e) {

        }
    }

}
