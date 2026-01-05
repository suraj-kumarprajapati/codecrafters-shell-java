package commands.impl;

import commands.ICommand;

public class Echo implements ICommand {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            StringBuilder output = new StringBuilder();

            for(int i=1; i<args.length; i++) {
                if(i > 1) output.append(" ");
                output.append(args[i]);
            }

            System.out.println(output.toString());
        }
        else {
            System.out.println("echo: missing operand");
        }
    }
    
}
