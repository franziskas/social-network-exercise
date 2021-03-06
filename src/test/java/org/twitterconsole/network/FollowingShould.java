package org.twitterconsole.network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.twitterconsole.posts.PostBuilder.anotherPost;
import static org.twitterconsole.posts.PostBuilder.onePost;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;
import static org.twitterconsole.posts.UserBuilder.aUserNamedBob;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;
import static org.twitterconsole.posts.output.WallOutputBuilder.anEmptyWallOutput;

import org.junit.Test;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;
import org.twitterconsole.posts.output.WallOutput;

public class FollowingShould {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();

    private PostRepository postRepository = mock(PostRepository.class);

    @Test
    public void collectAWallOutput() {
        Following following = new Following();
        following.addFollowing(BOB);

        PostsOutput alicesTimeline = anEmptyPostsOutput()
            .withPost(onePost())
            .create();
        when(postRepository.collectPosts(ALICE)).thenReturn(alicesTimeline);

        PostsOutput bobsTimeline = anEmptyPostsOutput()
            .withPost(anotherPost())
            .create();
        when(postRepository.collectPosts(BOB)).thenReturn(bobsTimeline);

        WallOutput actualOutput = following.collectWallOutput(postRepository, ALICE);

        assertThat(actualOutput, is(anEmptyWallOutput()
            .withTimeline(ALICE, alicesTimeline)
            .withTimeline(BOB, bobsTimeline)
            .create()));
    }
}
