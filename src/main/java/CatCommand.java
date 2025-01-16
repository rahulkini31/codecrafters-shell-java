////import java.io.BufferedReader;
////import java.io.FileReader;
////import java.io.IOException;
////
////public class CatCommand implements Command {
////    private final String[] args;
////
////    public CatCommand(String[] args) {
////        this.args = args;
////    }
////
////    @Override
////    public void execute() {
////        StringBuilder output = new StringBuilder();
////        for (String filePath : args) {
////            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
////                String line;
////                while ((line = reader.readLine()) != null) {
////                    output.append(line);
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////                return;
////            }
////        }
////        // Print the output without adding any extra characters
////        System.out.print(output.toString());
////    }
////}
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class CatCommand implements Command {
//    private final String[] args;
//
//    public CatCommand(String[] args) {
//        this.args = args;
//    }
//
//    @Override
//    public void execute() {
//        StringBuilder output = new StringBuilder();
//        for (String filePath : args) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    output.append(line);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//        }
//        // Print the output without adding any extra characters
//        System.out.print(output.toString());
//    }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CatCommand implements Command {
    private final String[] args;

    public CatCommand(String[] args) {
        this.args = args;
    }

    @Override
    public void execute() {
        StringBuilder output = new StringBuilder();
        for (String filePath : args) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        // Print the output and then a newline character
        System.out.print(output.toString());
        System.out.println();
    }
}

