import java.util.Scanner;

import commands.BuiltinCommandsResolver;
import commands.ExternalCommandsResolver;
import commands.ICommand;
import helpers.Parser;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.print("$ ");

            String input = scanner.nextLine();

            // parse the input
            Parser parser = new Parser(input);
            String[] newArgs = parser.parse();
            newArgs = parser.redirectArguments(newArgs);

            String commandName = newArgs[0];

            ICommand cmd = BuiltinCommandsResolver.get(commandName);
            if (cmd != null) {
                cmd.execute(newArgs);
            } else {

                ICommand exCommand = ExternalCommandsResolver.resolve(commandName);

                if (exCommand != null) {
                    exCommand.execute(newArgs);
                } else {
                    System.out.println(commandName + ": command not found");
                }
            }
        }

    }

}
