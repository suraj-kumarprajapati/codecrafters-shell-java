package commands;

import java.io.File;

import commands.impl.ExternalCommand;

public class ExternalCommandsResolver {

    public static ICommand resolve(String commandName) {

        // search in path
        String pathEnv = System.getenv("PATH");

        if(pathEnv == null) {
            return null;
        }

        String[] paths = pathEnv.split(":");

        for(String dir : paths) {
            File file = new File(dir, commandName);
            if(file.exists() && file.canExecute()) {
                return new ExternalCommand(file.getAbsolutePath());
            }
        }

        // if not found
        return null;
    }

}   
