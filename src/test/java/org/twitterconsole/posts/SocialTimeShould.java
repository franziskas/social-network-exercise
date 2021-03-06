package org.twitterconsole.posts;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SocialTimeShould {
    private SocialTime originalSocialTime;

    @Before
    public void setUp() {
        originalSocialTime = aTime()
            .create();
    }

    @Test
    public void printDifferenceToGivenTimestamp() {
        SocialTime timeOfPrinting = aTime()
            .plusMinutes(5)
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("5 minutes ago"));
    }

    @Test
    public void printOneMinuteDifference() {
        SocialTime timeOfPrinting = aTime()
            .plusSeconds(60)
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("1 minute ago"));
    }

    @Test
    public void printFifteenSecondDifference() {
        SocialTime timeOfPrinting = aTime()
            .plusSeconds(15)
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("15 seconds ago"));
    }

    @Test
    public void printOneSecondDifference() {
        SocialTime timeOfPrinting = aTime()
            .plusSeconds(1)
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("1 second ago"));
    }

    @Test
    public void beSortedLatestFirst() {
        SocialTime time = aTime().create();
        SocialTime laterTime = aTime().plusMinutes(1).create();
        List<SocialTime> times = asList(time, laterTime);

        sort(times);

        assertThat(times, contains(laterTime, time));
    }
}
