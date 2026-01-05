package commands;

import java.util.*;
import commands.impl.*;;



public class BuiltinCommandsResolver {
    private static final Map<String, ICommand> commands = new HashMap<>();

    static {
        commands.put("exit", new Exit());
        commands.put("echo", new Echo());
        commands.put("type", new Type());
    }

    public static ICommand get(String name) {
        return commands.get(name);
    }

    public static boolean getCommands(String command) {
        return commands.containsKey(command);
    }
}




