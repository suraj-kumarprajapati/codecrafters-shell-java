import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;

import org.jline.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import commands.BuiltinCommandsResolver;
import commands.ExternalCommandsResolver;
import commands.ICommand;
import helpers.BuiltinCompleter;
import helpers.Parser;
import helpers.Redirection;

public class Main {

    public static void main(String[] args) throws Exception {


        // jline functionalities
        Terminal terminal = TerminalBuilder
                .builder()
                .system(true)
                .build();

        StringsCompleter completer = new StringsCompleter("echo", "exit");

        LineReader reader = LineReaderBuilder
                .builder()
                .terminal(terminal)
                .completer(completer)
                .parser(new DefaultParser())
                .build();

        String prompt = "$ ";

        // print stream is console at the moment
        // save console before redirection
        PrintStream console = System.out;
        PrintStream error = System.err;

        while (true) {

            String input = reader.readLine(prompt);

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
