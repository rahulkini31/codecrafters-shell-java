//// src/main/java/InputParser.java
//import java.util.List;
//
//public class InputParser {
//    public Command parseCommand(String input, PathEnv pathEnv) {
//        List<String> argsList = QuoteHandler.parseArguments(input);
//        String commandString = argsList.isEmpty() ? "" : argsList.remove(0);
//
//        String[] args = argsList.toArray(new String[0]);
//        Command command = switch (commandString) {
//            case "exit" -> new ExitCommand(args);
//            case "echo" -> new EchoCommand(args);
//            case "type" -> new TypeCommand(args, pathEnv);
//            case "pwd" -> new PrintWorkingDirectoryCommand();
//            case "cd" -> new ChangeDirectoryCommand(args);
//            case "cat" -> new CatCommand(args);
//            default -> new UnknownCommand(commandString, args, pathEnv);
//        };
//        return command;
//    }
//}

// src/main/java/InputParser.java
import java.util.List;

public class InputParser {
    public Command parseCommand(String input, PathEnv pathEnv) {
        List<String> argsList = QuoteHandler.parseArguments(input);
        String commandString = argsList.isEmpty() ? "" : argsList.remove(0);

        String[] args = argsList.toArray(new String[0]);
        Command command = switch (commandString) {
            case "exit" -> new ExitCommand(args);
            case "echo" -> new EchoCommand(args);
            case "type" -> new TypeCommand(args, pathEnv);
            case "pwd" -> new PrintWorkingDirectoryCommand();
            case "cd" -> new ChangeDirectoryCommand(args);
            case "cat" -> new CatCommand(args);
            default -> new UnknownCommand(commandString, args, pathEnv);
        };
        return command;
    }
}