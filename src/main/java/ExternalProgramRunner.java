import java.io.File;
import java.io.IOException;

public class ExternalProgramRunner {
    public void run(String command, String[] args) {
        String path = System.getenv("PATH");
        String[] directories = path.split(":");
        boolean found = false;

        for (String dir : directories) {
            File file = new File(dir, command);
            if (file.exists() && file.canExecute()) {
                found = true;
                try {
                    String[] commandParts = new String[args.length + 1];
                    commandParts[0] = command;
                    System.arraycopy(args, 0, commandParts, 1, args.length);

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
}