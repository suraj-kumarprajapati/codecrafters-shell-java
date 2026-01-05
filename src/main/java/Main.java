import java.util.Scanner;

import commands.BuiltinCommandsResolver;
import commands.ExternalCommandsResolver;
import commands.ICommand;
import helpers.Parser;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Parser parser = null;

        while (true) {
            System.out.print("$ ");

            String input = scanner.nextLine();
            

            // (advanced)
            parser = new Parser(input);
            // String[] newArgs = input.split("\\s+");
            String[] newArgs = parser.parse();

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
