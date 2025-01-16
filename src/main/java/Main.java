//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        // Print the shell prompt
//        System.out.print("$ ");
//
//        // Create a Scanner object to read user input
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//
//        // Start an infinite loop to continuously read and process user input
//        while (true) {
//            // Check if the input starts with "exit"
//            if (input.startsWith("exit")) {
//                String[] parts = input.split(" ");
//                // If the command is "exit 0", terminate the program with status code 0
//                if (parts.length == 2 && parts[1].equals("0")) {
//                    scanner.close();
//                    System.exit(0);
//                } else {
//                    // Print usage message if the command is not "exit 0"
//                    System.out.println("Usage: exit 0");
//                }
//            }
//            // Check if the input starts with "echo"
//            else if (input.startsWith("echo")) {
//                // Print the text following the "echo" command
//                System.out.println(input.substring(5));
//            }
//            // Check if the input starts with "type"
//            else if (input.startsWith("type")) {
//                String[] parts = input.split(" ");
//                if (parts.length == 2) {
//                    String command = parts[1];
//                    // Check if the command is a shell builtin
//                    if (command.equals("echo") || command.equals("exit") || command.equals("type") || command.equals("pwd")) {
//                        System.out.println(command + " is a shell builtin");
//                    } else {
//                        // Search for the command in the directories listed in the PATH environment variable
//                        String path = System.getenv("PATH");
//                        String[] directories = path.split(":");
//                        boolean found = false;
//                        for (String dir : directories) {
//                            File file = new File(dir, command);
//                            // If the command is found and is executable, print its path
//                            if (file.exists() && file.canExecute()) {
//                                System.out.println(command + " is " + file.getAbsolutePath());
//                                found = true;
//                                break;
//                            }
//                        }
//                        // Print not found message if the command is not found in PATH
//                        if (!found) {
//                            System.out.println(command + ": not found");
//                        }
//                    }
//                } else {
//                    // Print usage message if the command format is incorrect
//                    System.out.println("Usage: type <command>");
//                }
//            }
//            // Check if the input is "pwd"
//            else if (input.equals("pwd")) {
//                // Print the current working directory
//                System.out.println(System.getProperty("user.dir"));
//            }
//            // Check if the input starts with "cd"
//            else if (input.startsWith("cd")) {
//                String[] parts = input.split(" ");
//                if (parts.length == 2) {
//                    File dir = new File(parts[1]);
//                    // Check if the directory exists and is a directory
//                    if (dir.exists() && dir.isDirectory()) {
//                        // Change the current working directory
//                        System.setProperty("user.dir", dir.getAbsolutePath());
//                    } else {
//                        // Print error message if the directory does not exist
//                        System.out.println("cd: " + parts[1] + ": No such file or directory");
//                    }
//                } else {
//                    // Print usage message if the command format is incorrect
//                    System.out.println("Usage: cd <directory>");
//                }
//            }
//            // Handle external commands
//            else {
//                String[] commandParts = input.split(" ");
//                String command = commandParts[0];
//                String path = System.getenv("PATH");
//                String[] directories = path.split(":");
//                boolean found = false;
//                for (String dir : directories) {
//                    File file = new File(dir, command);
//                    // If the command is found and is executable, execute it
//                    if (file.exists() && file.canExecute()) {
//                        found = true;
//                        try {
//                            // Create a ProcessBuilder to run the command with arguments
//                            ProcessBuilder pb = new ProcessBuilder(commandParts);
//                            pb.directory(new File(System.getProperty("user.dir")));
//                            pb.inheritIO();
//                            Process process = pb.start();
//                            // Wait for the process to complete
//                            process.waitFor();
//                        } catch (IOException | InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                }
//                // Print not found message if the command is not found in PATH
//                if (!found) {
//                    System.out.println(command + ": command not found");
//                }
//            }
//
//            // Print the shell prompt again for the next command
//            System.out.print("$ ");
//            // Read the next user input
//            input = scanner.nextLine();
//        }
//    }
//}
//
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Print the shell prompt
        System.out.print("$ ");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Start an infinite loop to continuously read and process user input
        while (true) {
            // Check if the input starts with "exit"
            if (input.startsWith("exit")) {
                String[] parts = input.split(" ");
                // If the command is "exit 0", terminate the program with status code 0
                if (parts.length == 2 && parts[1].equals("0")) {
                    scanner.close();
                    System.exit(0);
                } else {
                    // Print usage message if the command is not "exit 0"
                    System.out.println("Usage: exit 0");
                }
            }
            // Check if the input starts with "echo"
            else if (input.startsWith("echo")) {
                // Print the text following the "echo" command
                System.out.println(input.substring(5));
            }
            // Check if the input starts with "type"
            else if (input.startsWith("type")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    String command = parts[1];
                    // Check if the command is a shell builtin
                    if (command.equals("echo") || command.equals("exit") || command.equals("type") || command.equals("pwd")) {
                        System.out.println(command + " is a shell builtin");
                    } else {
                        // Search for the command in the directories listed in the PATH environment variable
                        String path = System.getenv("PATH");
                        String[] directories = path.split(":");
                        boolean found = false;
                        for (String dir : directories) {
                            File file = new File(dir, command);
                            // If the command is found and is executable, print its path
                            if (file.exists() && file.canExecute()) {
                                System.out.println(command + " is " + file.getAbsolutePath());
                                found = true;
                                break;
                            }
                        }
                        // Print not found message if the command is not found in PATH
                        if (!found) {
                            System.out.println(command + ": not found");
                        }
                    }
                } else {
                    // Print usage message if the command format is incorrect
                    System.out.println("Usage: type <command>");
                }
            }
            // Check if the input is "pwd"
            else if (input.equals("pwd")) {
                // Print the current working directory
                System.out.println(System.getProperty("user.dir"));
            }
            // Check if the input starts with "cd"
            else if (input.startsWith("cd")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    File dir = new File(parts[1]);
                    // Check if the directory exists and is a directory
                    if (dir.exists() && dir.isDirectory()) {
                        // Change the current working directory
                        System.setProperty("user.dir", dir.getAbsolutePath());
                    } else {
                        // Handle relative paths
                        dir = new File(System.getProperty("user.dir"), parts[1]);
                        if (dir.exists() && dir.isDirectory()) {
                            // Change the current working directory
                            System.setProperty("user.dir", dir.getAbsolutePath());
                        } else {
                            // Print error message if the directory does not exist
                            System.out.println("cd: " + parts[1] + ": No such file or directory");
                        }
                    }
                } else {
                    // Print usage message if the command format is incorrect
                    System.out.println("Usage: cd <directory>");
                }
            }
            // Handle external commands
            else {
                String[] commandParts = input.split(" ");
                String command = commandParts[0];
                String path = System.getenv("PATH");
                String[] directories = path.split(":");
                boolean found = false;
                for (String dir : directories) {
                    File file = new File(dir, command);
                    // If the command is found and is executable, execute it
                    if (file.exists() && file.canExecute()) {
                        found = true;
                        try {
                            // Create a ProcessBuilder to run the command with arguments
                            ProcessBuilder pb = new ProcessBuilder(commandParts);
                            pb.directory(new File(System.getProperty("user.dir")));
                            pb.inheritIO();
                            Process process = pb.start();
                            // Wait for the process to complete
                            process.waitFor();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                // Print not found message if the command is not found in PATH
                if (!found) {
                    System.out.println(command + ": command not found");
                }
            }

            // Print the shell prompt again for the next command
            System.out.print("$ ");
            // Read the next user input
            input = scanner.nextLine();
        }
    }
}