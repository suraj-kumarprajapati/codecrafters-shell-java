package commands;

import java.util.*;

import commands.impl.Type;




public class Commands {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("exit", new Exit());
        commands.put("echo", new Echo());
        commands.put("type", new Type());
    }

    public static Command get(String name) {
        return commands.get(name);
    }

    public static boolean getCommands(String command) {
        return commands.containsKey(command);
    }
}




