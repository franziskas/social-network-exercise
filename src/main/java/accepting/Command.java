package accepting;

public class Command {

    private static final String COMMAND_SEPERATOR = " ";
    private String user;

    public Command(String command) {
        String[] commandParts = command.split(COMMAND_SEPERATOR);
        user = commandParts[0];
    }

    public String getUser() {
        return user;
    }

    public Object getMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
