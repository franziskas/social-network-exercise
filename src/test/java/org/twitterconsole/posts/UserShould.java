package org.twitterconsole.posts;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.io.CommandBuilder.aCommand;

import org.junit.Test;
import org.twitterconsole.io.Command;

public class UserShould {
    @Test
    public void printToTheUsersName() {
        Command parameter = aCommand().withUser("Alice").create();

        User user = new User(parameter);

        assertThat(user.toString(), is("Alice"));
    }
}
