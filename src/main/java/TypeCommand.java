import java.io.File;

public class TypeCommand implements Command {
    private final String[] args;
    private final PathEnv pathEnv;

    public TypeCommand(String[] args, PathEnv pathEnv) {
        this.args = args;
        this.pathEnv = pathEnv;
    }

    @Override
    public void execute() {
        if (args.length == 1) {
            String command = args[0];
            if (command.equals("echo") || command.equals("exit") || command.equals("type") || command.equals("pwd")) {
                System.out.println(command + " is a shell builtin");
            } else {
                String path = pathEnv.getPath();
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
    }
}