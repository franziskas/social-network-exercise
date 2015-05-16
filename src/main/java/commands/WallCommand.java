package commands;

import timeline.TimelineService;
import timeline.User;
import timeline.WallOutput;

public class WallCommand implements CommandWithOutput {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;

    public WallCommand(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(WALL_IDENTIFIER);
    }

    @Override
    public WallOutput executeCommand(CommandParameter commandParameter) {
        return timelineService.collectWall(new User(commandParameter));
    }

}
