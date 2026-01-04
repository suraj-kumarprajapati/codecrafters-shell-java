import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");

            String command = scanner.nextLine();

            switch (command) {
                case "exit":
                    break;

                case "echo":
                    String output = command.split(" ", 2)[1];
                    System.out.println(output);
            
                default:
                    break;
            }

            System.out.println(command + ": command not found");

        }

        scanner.close();

    }
}
