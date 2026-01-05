package commands.impl;

import commands.ICommand;

public class Exit implements ICommand {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.exit(Integer.parseInt(args[1]));
        else System.exit(0);
    }

}
