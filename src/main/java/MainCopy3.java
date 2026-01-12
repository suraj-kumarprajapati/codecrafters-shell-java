import commands.BuiltinCommandsResolver;
import commands.ExternalCommandsResolver;
import commands.ICommand;
import parser.Parser;
import redirection.Redirection;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;

public class MainCopy3 {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        String prompt = "$ ";

        // print stream is console at the moment
        // save console before redirection
        PrintStream console = System.out;
        PrintStream error = System.err;

        while (true) {

            System.out.print(prompt);

            String input = scanner.nextLine();

            // parse the input
            Parser parser = new Parser(input);
            String[] tokens = parser.parse();

            // redirect the arguments
            Redirection redirection = new Redirection(tokens, Path.of("."));
            String[] newArgs = redirection.redirectArguments();

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

            // set print stream to console
            System.setOut(console);
            System.setErr(error);
        }

    }

}
