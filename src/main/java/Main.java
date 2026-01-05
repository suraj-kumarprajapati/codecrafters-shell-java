import java.util.Scanner;

import commands.BuiltinCommandsResolver;
import commands.ExternalCommandsResolver;
import commands.ICommand;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");

            String input = scanner.nextLine();
            String[] splittedInput = input.split(" ", 2);

            // (advanced) - for external commands
            String[] tokens = input.split("\\s+");

            String commandName = splittedInput[0];

            String[] newArgs;
            if (splittedInput.length > 1) {
                newArgs = new String[] { commandName, splittedInput[1] };
            } else {
                newArgs = new String[] { commandName };
            }

            ICommand cmd = BuiltinCommandsResolver.get(commandName);
            if (cmd != null) {
                cmd.execute(newArgs);
            } else {

                ICommand exCommand = ExternalCommandsResolver.resolve(commandName);

                if (exCommand != null) {
                    exCommand.execute(tokens);
                } else {
                    System.out.println(commandName + ": command not found");
                }
            }
        }

    }

}
