package timeline;

import static commands.CommandParameterBuilder.aPostCommand;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.WallOutputBuilder.anEmptyWallOutput;
import static timeline.builder.OutputBuilder.anEmptyOutput;
import static timeline.builder.OutputBuilder.anOutput;
import static timeline.builder.PostBuilder.aPost;
import static timeline.builder.SocialTimeBuilder.aTime;
import static timeline.builder.UserBuilder.aUser;

import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;

public class TimelineServiceTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final User BOB = aUser().withName("Bob").create();
    private static final SocialTime TIME = aTime().create();
    private static final Output OUTPUT = anOutput().create();
    private static final Message MESSAGE = new Message(aPostCommand().create());

    private Timelines timelines = mock(Timelines.class);
    private SocialNetwork network = mock(SocialNetwork.class);
    private SocialTimeClock clock = mock(SocialTimeClock.class);

    private TimelineService timelineService = new TimelineService(timelines, network, clock);

    @Test
    public void itDelegatesPostsToTimelinesIncludingTheCurrentTime() {
        when(clock.getLocalDateTime()).thenReturn(TIME);

        timelineService.post(ALICE, MESSAGE);

        Post expectedPost = aPost().withMessage(MESSAGE.toString()).withPostingTime(TIME).create();
        verify(timelines).post(ALICE, expectedPost);
    }

    @Test
    public void itDelegatesPrintTimelineToTimelines() {
        when(timelines.collectPosts(ALICE)).thenReturn(OUTPUT);

        Output actualOutput = timelineService.collectPosts(ALICE);

        assertThat(actualOutput, is(OUTPUT));
    }

    @Test
    public void itRegisteresANewFollowingInTheSocialNetwork() {
        timelineService.registerFollowing(BOB, ALICE);

        verify(network).registerFollowing(BOB, ALICE);
    }

    @Test
    public void itCollectsTimelinesFromUserAndFollowingIntoAWall() {
        when(network.getFollowing(ALICE)).thenReturn(asList(BOB));

        Output alicesTimeline = anEmptyOutput() //
            .withPost(aPost().withMessage("message1").create()) //
            .withPost(aPost().withMessage("message2").create()) //
            .create();
        when(timelines.collectPosts(ALICE)).thenReturn(alicesTimeline);

        Output bobsTimeline = anEmptyOutput() //
            .withPost(aPost().withMessage("message3").create()) //
            .withPost(aPost().withMessage("message4").create()) //
            .create();
        when(timelines.collectPosts(BOB)).thenReturn(bobsTimeline);

        Output actualOutput = timelineService.collectWall(ALICE);

        assertThat(actualOutput, is(anEmptyWallOutput() //
            .withTimeline(ALICE, alicesTimeline) //
            .withTimeline(BOB, bobsTimeline) //
            .create()));
    }
}
