package org.twitterconsole.posts;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessageTest {

    @Test
    public void givenAMessageAsTextItReturnsAMessageObjectWithThatString() {
        Message message = new Message("I love the weather today");

        assertThat(message.toString(), is("I love the weather today"));
    }
}
