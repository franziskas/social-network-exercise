package org.twitterconsole.application;


public class StartTwitterConsole {

    public static void main(String args[]) {
        TwitterConsole application = new TwitterConsoleFactory().create();
        application.start();
    }

}