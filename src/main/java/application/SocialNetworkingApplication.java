package application;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplication {

    private SocialNetworkingConsole console;
    private Commands commands;

    public SocialNetworkingApplication(Commands commands, SocialNetworkingConsole console) {
        this.commands = commands;
        this.console = console;
    }

    public void start() {
        console.print("Welcome to my social network application");
        while (true) {
            CommandParameter command = console.getNextCommand();
            if (command == CommandParameter.NOTHING)
                continue;
            if (command.isEmpty())
                return;
            commands.execute(command);
        }
    }
}
