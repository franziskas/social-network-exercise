package org.twitterconsole.posts;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;

import java.time.Instant;
import java.time.LocalDateTime;

public class SocialTimeBuilder {

    private LocalDateTime timestamp = now();

    public static SocialTimeBuilder aTime() {
        return new SocialTimeBuilder();
    }

    public SocialTimeBuilder withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public SocialTimeBuilder withTimestamp(Instant instant) {
        return withTimestamp(ofInstant(instant, systemDefault()));
    }

    public SocialTimeBuilder plusMinutes(int numberOfMinutes) {
        timestamp = timestamp.plusMinutes(numberOfMinutes);
        return this;
    }

    public SocialTimeBuilder plusSeconds(int numberOfSeconds) {
        timestamp = timestamp.plusSeconds(numberOfSeconds);
        return this;
    }

    public SocialTime create() {
        return new SocialTime(timestamp);
    }

}
