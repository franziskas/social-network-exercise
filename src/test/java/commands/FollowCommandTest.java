package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FollowCommandTest {
    private FollowCommand command = new FollowCommand();

    @Test
    public void itIsApplicableIfItContainsFollows() {
        CommandParameter commandParameter = aCommand().withCommand(" follows ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfItDoesntContainFollows() {
        CommandParameter commandParameter = aCommand().withCommand("").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }
}
