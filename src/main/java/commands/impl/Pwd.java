package commands.impl;

import commands.ICommand;

public class Pwd implements ICommand {
    @Override
    public void execute(String[] args) {
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
    }
}
