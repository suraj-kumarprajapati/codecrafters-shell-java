package commands.impl;

import commands.ICommand;

public class Echo implements ICommand {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println(args[1]);
        else System.out.println("echo: missing operand");
    }
    
}
