package accepting;

import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimelinesTest {
    private static final String ALICE = "Alice";
    private static final SocialTime PRINTING_TIME = aTime().create();
    private static final Post A_POST = aPost().create();

    private Timelines timelines = new Timelines();

    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        Timeline alicesTimeline = timelines.getPostsFor(ALICE);

        assertThat(alicesTimeline.printTimeline(PRINTING_TIME), is(empty()));
    }

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        timelines.post(ALICE, A_POST);

        Timeline alicesTimeline = timelines.getPostsFor(ALICE);

        assertThat(alicesTimeline.printTimeline(PRINTING_TIME), contains(A_POST.printAt(PRINTING_TIME)));
    }

    @Test
    public void givenAliceAndBobPublishedPostsWhenAliceTimelineIsRequestedOnlyHerPostIsReturned() {
        timelines.post(ALICE, A_POST);
        timelines.post("Bob", aPost().withMessage("another post").create());

        Timeline alicesTimeline = timelines.getPostsFor(ALICE);

        assertThat(alicesTimeline.printTimeline(PRINTING_TIME), contains(A_POST.printAt(PRINTING_TIME)));
    }

}