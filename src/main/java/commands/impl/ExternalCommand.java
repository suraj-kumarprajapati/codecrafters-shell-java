package commands.impl;

import commands.ICommand;

public class ExternalCommand implements ICommand {

    private final String executablePath;

    public ExternalCommand(String executablePath) {
        this.executablePath = executablePath;
    }

    @Override
    public void execute(String[] args) {
        
        try {
            // Replace command name with absolute executable path
            args[0] = executablePath; 
            
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
