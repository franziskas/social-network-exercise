package commons;

import static java.time.LocalDateTime.now;

import java.time.Clock;

import accepting.SocialTime;

public class SocialTimeClock {

    private Clock clock;

    public SocialTimeClock(Clock clock) {
        this.clock = clock;
    }

    public SocialTime getLocalDateTime() {
        return new SocialTime(now(clock));
    }

}