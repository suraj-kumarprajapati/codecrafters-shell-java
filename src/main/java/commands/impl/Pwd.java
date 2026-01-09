package commands.impl;

import commands.ICommand;
import environment.Environment;
import environment.SystemEnvironment;

import java.nio.file.Path;

public class Pwd implements ICommand {

    private final Environment environment;

    public Pwd(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute(String[] args) {
        Path path = environment.getCurrentDirectory().toAbsolutePath();
        System.out.println(path);
    }
}
