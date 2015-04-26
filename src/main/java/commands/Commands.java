package commands;

import java.util.List;

import time.SocialTime;
import timeline.Output;

public class Commands {
    private List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public Output execute(CommandParameter commandParameter, SocialTime time) {
        User user = new User(commandParameter);
        return execute(time, user, commandParameter);
    }

    private Output execute(SocialTime time, User user, CommandParameter commandParameter) {
        return commands.stream() //
            .filter(candidate -> candidate.isApplicable(commandParameter)) //
            .findFirst() //
            .map(command -> command.executeCommand(user, commandParameter, time)) //
            .orElse(new Output());
    }
}
