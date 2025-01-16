import java.util.Scanner;

public class Main {
    private static String previousDir = System.getProperty("user.dir");

    public static String getPreviousDir() {
        return previousDir;
    }

    public static void setPreviousDir(String dir) {
        previousDir = dir;
    }

    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        PathEnv pathEnv = new PathEnv();

        ExternalProgramRunner runner = new ExternalProgramRunner();

        while (true) {
            InputParser parser = new InputParser();
            Command command = parser.parseCommand(input, pathEnv);

            if (command instanceof UnknownCommand) {
                String[] commandParts = input.split(" ");
                runner.run(commandParts[0], commandParts.length > 1 ? commandParts[1].split(" ") : new String[0]);
            } else {
                command.execute();
            }

            System.out.print("$ ");
            input = scanner.nextLine();
        }
    }
}