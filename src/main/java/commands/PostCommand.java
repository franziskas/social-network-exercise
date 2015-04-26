package commands;

import timeline.Message;
import timeline.Output;
import timeline.TimelineService;

public class PostCommand implements Command {
    private static final String POSTING_COMMAND = " -> ";

    private TimelineService timelines;

    public PostCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter command) {
        return command.startsWith(POSTING_COMMAND);
    }

    @Override
    public Output executeCommand(User user, CommandParameter command) {
        timelines.post(user, new Message(command));
        return new Output();
    }
}
