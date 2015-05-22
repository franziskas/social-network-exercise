package org.twitterconsole.io;

import java.util.Scanner;

public class SocialNetworkingConsole {

    private static final String PROMPT = "> ";

    public void print(Iterable<? extends String> output) {
        output.forEach(line -> System.out.println(line));
    }

    public Command getNextCommand() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return new Command(scanner.nextLine());
        return Command.NOTHING;
    }

    public void printPrompt() {
        System.out.print(PROMPT);
    }

}