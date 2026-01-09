package commands.impl;

import commands.ICommand;
import environment.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cd implements ICommand {

    private final Environment environment;

    public Cd(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void execute(String[] args) {

        if(args.length < 2) {
            System.out.println("cd: missing directory argument.");
            return;
        }

        String targetDir = args[1];
        Path newPath = Paths.get(targetDir);

        environment.changeDirectory(newPath);
    }




}
