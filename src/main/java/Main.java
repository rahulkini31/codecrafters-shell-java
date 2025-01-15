//import java.io.File;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        System.out.print("$ ");
//
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//
//        while (true) {
//            if (input.startsWith("exit")) {
//                String[] parts = input.split(" ");
//                if (parts.length == 2 && parts[1].equals("0")) {
//                    scanner.close();
//                    System.exit(0);
//                } else {
//                    System.out.println("Usage: exit 0");
//                }
//            } else if (input.startsWith("echo")) {
//                System.out.println(input.substring(5));
//            } else if (input.startsWith("type")) {
//                String[] parts = input.split(" ");
//                if (parts.length == 2) {
//                    String command = parts[1];
//                    if (command.equals("echo") || command.equals("exit") || command.equals("type")) {
//                        System.out.println(command + " is a shell builtin");
//                    } else {
//                        String path = System.getenv("PATH");
//                        String[] directories = path.split(":");
//                        boolean found = false;
//                        for (String dir : directories) {
//                            File file = new File(dir, command);
//                            if (file.exists() && file.canExecute()) {
//                                System.out.println(command + " is " + file.getAbsolutePath());
//                                found = true;
//                                break;
//                            }
//                        }
//                        if (!found) {
//                            System.out.println(command + ": not found");
//                        }
//                    }
//                } else {
//                    System.out.println("Usage: type <command>");
//                }
//            } else {
//                System.out.println(input + ": command not found");
//            }
//
//            System.out.print("$ ");
//            input = scanner.nextLine();
//        }
//    }
//}
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) {
            if (input.startsWith("exit")) {
                String[] parts = input.split(" ");
                if (parts.length == 2 && parts[1].equals("0")) {
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Usage: exit 0");
                }
            } else if (input.startsWith("echo")) {
                System.out.println(input.substring(5));
            } else if (input.startsWith("type")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    String command = parts[1];
                    if (command.equals("echo") || command.equals("exit") || command.equals("type")) {
                        System.out.println(command + " is a shell builtin");
                    } else {
                        String path = System.getenv("PATH");
                        String[] directories = path.split(":");
                        boolean found = false;
                        for (String dir : directories) {
                            File file = new File(dir, command);
                            if (file.exists() && file.canExecute()) {
                                System.out.println(command + " is " + file.getAbsolutePath());
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println(command + ": not found");
                        }
                    }
                } else {
                    System.out.println("Usage: type <command>");
                }
            } else {
                // Handle external commands
                String[] commandParts = input.split(" ");
                String command = commandParts[0];
                String path = System.getenv("PATH");
                String[] directories = path.split(":");
                boolean found = false;
                for (String dir : directories) {
                    File file = new File(dir, command);
                    if (file.exists() && file.canExecute()) {
                        found = true;
                        try {
                            ProcessBuilder pb = new ProcessBuilder(commandParts);
                            pb.directory(new File(System.getProperty("user.dir")));
                            pb.inheritIO();
                            Process process = pb.start();
                            process.waitFor();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                if (!found) {
                    System.out.println(command + ": command not found");
                }
            }

            System.out.print("$ ");
            input = scanner.nextLine();
        }
    }
}