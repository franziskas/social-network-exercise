package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.Message;
import org.twitterconsole.posts.User;
import org.twitterconsole.time.SocialNetworkingClock;

public class PostAction implements Action {
    private static final String POST_IDENTIFIER = " -> ";

    private TimelineService timelineService;
    private SocialNetworkingClock clock;

    public PostAction(TimelineService timelineService, SocialNetworkingClock clock) {
        this.timelineService = timelineService;
        this.clock = clock;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command)) {
            String message = command.afterIdentifier(POST_IDENTIFIER);
            timelineService.post(new User(command), new Message(message), clock.getLocalDateTime());
        }
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(POST_IDENTIFIER);
    }
}
