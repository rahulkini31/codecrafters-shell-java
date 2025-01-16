import java.io.File;
import java.io.IOException;

public class UnknownCommand implements Command {
    private final String command;
    private final String[] args;
    private final PathEnv pathEnv;

    public UnknownCommand(String command, String[] args, PathEnv pathEnv) {
        this.command = command;
        this.args = args;
        this.pathEnv = pathEnv;
    }

    @Override
    public void execute() {
        String path = pathEnv.getPath();
        String[] directories = path.split(":");
        boolean found = false;
        for (String dir : directories) {
            File file = new File(dir, command);
            if (file.exists() && file.canExecute()) {
                found = true;
                try {
                    ProcessBuilder pb = new ProcessBuilder(args);
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