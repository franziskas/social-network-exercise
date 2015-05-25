package org.twitterconsole.application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import org.twitterconsole.action.Actions;
import org.twitterconsole.action.available.DisplayTimelineAction;
import org.twitterconsole.action.available.DisplayWallAction;
import org.twitterconsole.action.available.FollowAction;
import org.twitterconsole.action.available.PostAction;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.network.SocialNetwork;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.network.Timelines;
import org.twitterconsole.time.SocialNetworkingClock;

public final class TwitterConsoleFactory {
    private TwitterConsoleFactory() {
    }

    public static TwitterConsole createTwitterConsole() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();
        Actions actions = createStandardActions(systemDefaultZone(), console);
        return new TwitterConsole(actions, console);
    }

    public static Actions createStandardActions(Clock clock, SocialNetworkingConsole console) {
        return getActions(new SocialNetworkingClock(clock), console);
    }

    private static Actions getActions(SocialNetworkingClock clock, SocialNetworkingConsole console) {
        TimelineService timelineService = new TimelineService(new Timelines(), new SocialNetwork());

        return new Actions(asList(
            new PostAction(timelineService, clock),
            new DisplayTimelineAction(timelineService, clock, console),
            new FollowAction(timelineService),
            new DisplayWallAction(timelineService, console, clock)));
    }
}
