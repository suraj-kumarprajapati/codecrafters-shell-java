package commands;

import java.util.*;
import commands.impl.*;
import environment.Environment;
import environment.SystemEnvironment;;



public class BuiltinCommandsResolver {
    private static final Map<String, ICommand> commands = new HashMap<>();
    private static final Environment environment = new SystemEnvironment();

    static {
        commands.put("exit", new Exit());
        commands.put("echo", new Echo());
        commands.put("type", new Type());
        commands.put("pwd", new Pwd(environment));
        commands.put("cd", new Cd(environment));
    }

    public static ICommand get(String name) {
        return commands.get(name);
    }

    public static boolean getCommands(String command) {
        return commands.containsKey(command);
    }
}




