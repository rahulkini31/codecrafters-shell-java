//import java.util.ArrayList;
//import java.util.List;
//
//public class InputParser {
//    public Command parseCommand(String input, PathEnv pathEnv) {
//        List<String> argsList = new ArrayList<>();
//        String commandString = "";
//        int i = 0;
//        StringBuilder sb = new StringBuilder();
//        boolean inQuotes = false;
//
//        while (i < input.length()) {
//            char c = input.charAt(i);
//
//            if (c == '\'') {
//                inQuotes = !inQuotes;
//                i++;
//                continue;
//            }
//
//            if (Character.isWhitespace(c) && !inQuotes) {
//                if (sb.length() > 0) {
//                    if (commandString.isEmpty()) {
//                        commandString = sb.toString();
//                    } else {
//                        argsList.add(sb.toString());
//                    }
//                    sb = new StringBuilder();
//                }
//                i++;
//                continue;
//            }
//
//            sb.append(c);
//            i++;
//        }
//
//        if (sb.length() > 0) {
//            if (commandString.isEmpty()) {
//                commandString = sb.toString();
//            } else {
//                argsList.add(sb.toString());
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
//            case "cat" -> new CatCommand(args);
//            default -> new UnknownCommand(commandString, args, pathEnv);
//        };
//        return command;
//    }
//}

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public Command parseCommand(String input, PathEnv pathEnv) {
        List<String> argsList = new ArrayList<>();
        String commandString = "";
        int i = 0;
        StringBuilder sb = new StringBuilder();
        boolean inSingleQuotes = false;
        boolean inDoubleQuotes = false;

        while (i < input.length()) {
            char c = input.charAt(i);

            if (c == '\'') {
                if (inDoubleQuotes) {
                    sb.append(c);
                } else {
                    inSingleQuotes = !inSingleQuotes;
                }
                i++;
                continue;
            }

            if (c == '\"') {
                inDoubleQuotes = !inDoubleQuotes;
                i++;
                continue;
            }

            if (c == '\\' && inDoubleQuotes && i + 1 < input.length()) {
                char nextChar = input.charAt(i + 1);
                if (nextChar == '\\' || nextChar == '$' || nextChar == '\"' || nextChar == '\n') {
                    sb.append(nextChar);
                    i += 2;
                    continue;
                }
            }

            if (Character.isWhitespace(c) && !inSingleQuotes && !inDoubleQuotes) {
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