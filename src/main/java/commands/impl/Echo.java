package commands.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import commands.ICommand;

public class Echo implements ICommand {

    @Override
    public void execute(String[] args) {
        int n = args.length;

        StringBuilder output = new StringBuilder();

        if (n > 2 && isRedirectOperator(args[n - 2])) {

            String file = args[n - 1];

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                for (int i = 1; i < n - 2; i++) {
                    if (i > 1)
                        output.append(" ");
                    output.append(args[i]);
                }

                writer.write(output.toString());
                writer.newLine();
            } catch (IOException e) {
                System.out.println("echo: nonexistent: No such file or directory");
            }

        } else if (n > 1) {

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

    private boolean isRedirectOperator(String arg) {
        if (arg.equals(">") || arg.equals("1>")) {
            return true;
        }

        return false;
    }
}
