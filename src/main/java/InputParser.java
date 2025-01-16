//import java.util.ArrayList;
//import java.util.List;
//
//public class InputParser {
//    public Command parseCommand(String input, PathEnv pathEnv) {
//        List<String> argsList = new ArrayList<>();
//        String commandString = "";
//        int i = 0;
//        StringBuilder sb = new StringBuilder();
//
//        while (i < input.length()) {
//            // Remove preceding whitespace
//            while (i < input.length() && Character.isWhitespace(input.charAt(i))) {
//                i++;
//            }
//
//            // Get command
//            if (commandString.isEmpty()) {
//                while (i < input.length() && !Character.isWhitespace(input.charAt(i))) {
//                    sb.append(input.charAt(i));
//                    i++;
//                }
//                commandString = sb.toString();
//                sb = new StringBuilder();
//            }
//
//            // Get single quote arg
//            if (i < input.length() && input.charAt(i) == '\'') {
//                i++;
//                sb = new StringBuilder();
//                while (i < input.length() && input.charAt(i) != '\'') {
//                    sb.append(input.charAt(i));
//                    i++;
//                }
//                i++; // Skip closing quote
//                argsList.add(sb.toString());
//            }
//
//            // Get unquoted arg
//            if (i < input.length() && !Character.isWhitespace(input.charAt(i)) && input.charAt(i) != '\'') {
//                sb = new StringBuilder();
//                while (i < input.length() && !Character.isWhitespace(input.charAt(i))) {
//                    sb.append(input.charAt(i));
//                    i++;
//                }
//                argsList.add(sb.toString());
//            }
//
//            // Skip whitespace between args
//            while (i < input.length() && Character.isWhitespace(input.charAt(i))) {
//                i++;
//            }
//        }
//
//        String[] args = argsList.toArray(new String[0]);
//        Command command = switch (commandString) {
//            case "exit" -> new ExitCommand(args);
//            case "echo" -> new EchoCommand(args);
//            case "type" -> new TypeCommand(args, pathEnv);
//            case "pwd" -> new PrintWorkingDirectoryCommand();
//            case "cd" -> new ChangeDirectoryCommand(args);
//            default -> new UnknownCommand(commandString, args, pathEnv);
//        };
//        return command;
//    }
//}

//

//

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public Command parseCommand(String input, PathEnv pathEnv) {
        List<String> argsList = new ArrayList<>();
        String commandString = "";
        int i = 0;
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        while (i < input.length()) {
            char c = input.charAt(i);

            if (c == '\'') {
                inQuotes = !inQuotes;
                i++;
                continue;
            }

            if (Character.isWhitespace(c) && !inQuotes) {
                if (sb.length() > 0) {
                    if (commandString.isEmpty()) {
                        commandString = sb.toString();
                    } else {
                        argsList.add(sb.toString());
                    }
                    sb = new StringBuilder();
                }
                i++;
                continue;
            }

            sb.append(c);
            i++;
        }

        if (sb.length() > 0) {
            if (commandString.isEmpty()) {
                commandString = sb.toString();
            } else {
                argsList.add(sb.toString());
            }
        }

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