//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        // Uncomment this block to pass the first stage
//         System.out.print("$ ");
//
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//
//        do {
//            System.out.println(input + ": command not found");
//
//            System.out.print("$ ");
//
//            input = scanner.nextLine();
//        } while ( !input.matches(""));
//        scanner.close();
//    }
//}
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
//            } else {
//                System.out.println(input + ": command not found");
//            }
//
//            System.out.print("$ ");
//            input = scanner.nextLine();
//        }
//    }
//}

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
            } else {
                System.out.println(input + ": command not found");
            }

            System.out.print("$ ");
            input = scanner.nextLine();
        }
    }
}