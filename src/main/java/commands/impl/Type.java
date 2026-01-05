package commands.impl;

import java.io.File;

import commands.BuiltinCommandsResolver;
import commands.ICommand;



public class Type implements ICommand {

    // first argument is command and rest are arguments
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("type: missing operand");
            return;
        }

        String name = args[1];


        // search in hashmap for builtin type
        ICommand cmd = BuiltinCommandsResolver.get(name);
        if (cmd != null) {
            System.out.println(name + " is a shell builtin");
            return;
        } 

        // search in path for external command type
        String pathEnv = System.getenv("PATH");
        if(pathEnv == null) {
            System.out.println(name + ": not found");
            return;
        }

        String[] paths = pathEnv.split(":");
        for(String dir : paths) {
            File file = new File(dir, name);
            if(file.exists() && file.canExecute()) {
                System.out.println(name + " is " + file.getAbsolutePath());
                return;
            }
        }

        // Not found anywhere
        System.out.println(name + ": not found");

    }

}
