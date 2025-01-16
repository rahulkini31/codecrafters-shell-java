public class EchoCommand implements Command {
    private final String[] args;

    public EchoCommand(String[] args) {
        this.args = args;
    }

    @Override
    public void execute() {
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }
}