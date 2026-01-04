import java.util.Scanner;

import commands.Command;
import commands.Commands;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");

            String input = scanner.nextLine();
            String[] splittedInput = input.split(" ", 2);

            String commandName = splittedInput[0];

            String[] newArgs;
            if(splittedInput.length > 1) {
                newArgs = new String[] {commandName, splittedInput[1]};
            }
            else {
                newArgs = new String[] {commandName};
            }


            Command cmd = Commands.get(commandName);
            if (cmd != null) {
                cmd.execute(newArgs);
            } else {
                System.out.println(commandName + ": command not found");
            }

        }

    }

}
