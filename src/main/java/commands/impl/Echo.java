package commands.impl;

import commands.ICommand;

public class Echo implements ICommand {

    @Override
    public void execute(String[] args) {
        int n = args.length;
        StringBuilder output = new StringBuilder();

        if (n > 1) {
            for (int i = 1; i < n; i++) {
                if (i > 1)
                    output.append(" ");
                output.append(args[i]);
            }

            System.out.println(output.toString());
        } else {
            System.out.println("echo: missing operand");
        }
    }

}
