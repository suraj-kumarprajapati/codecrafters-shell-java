package commands;

import java.util.*;




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

class Type implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("type: missing operand");
            return;
        }

         String name = args[1];

        Command cmd = Commands.get(name);
        if (cmd != null) {
            System.out.println(name + " is a shell builtin");
        } else {
            System.out.println(name + " not found");
        }

    }
    
}

class Echo implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.out.println(args[1]);
        else System.out.println("echo: missing operand");
    }
    
}

class Exit implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length > 1) System.exit(Integer.parseInt(args[1]));
        else System.exit(0);
    }

}