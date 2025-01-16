import java.io.File;
import java.io.IOException;

public class ChangeDirectoryCommand implements Command {
    private final String[] args;

    public ChangeDirectoryCommand(String[] args) {
        this.args = args;
    }

    @Override
    public void execute() {
        if (args.length == 1) {
            String path = args[0];
            if (path.equals("~")) {
                path = System.getenv("HOME");
            } else if (path.equals("-")) {
                path = Main.getPreviousDir();
            }
            File dir = new File(path);
            try {
                if (!dir.isAbsolute()) {
                    dir = new File(System.getProperty("user.dir"), path);
                }
                if (dir.exists() && dir.isDirectory()) {
                    Main.setPreviousDir(System.getProperty("user.dir"));
                    System.setProperty("user.dir", dir.getCanonicalPath());
                } else {
                    System.out.println("cd: " + path + ": No such file or directory");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: cd <directory>");
        }
    }
}