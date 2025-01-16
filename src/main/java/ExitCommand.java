public class ExitCommand implements Command {
    private final String[] args;

    public ExitCommand(String[] args) {
        this.args = args;
    }

    @Override
    public void execute() {
        if (args.length == 1 && args[0].equals("0")) {
            System.exit(0);
        } else {
            System.out.println("Usage: exit 0");
        }
    }
}