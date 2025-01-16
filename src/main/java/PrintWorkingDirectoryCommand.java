public class PrintWorkingDirectoryCommand implements Command {
    @Override
    public void execute() {
        System.out.println(System.getProperty("user.dir"));
    }
}