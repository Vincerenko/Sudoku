package org.example;

import org.example.config.Launcher;

public class Main {

    public static void main(String[] args) {
//        int port = Integer.parseInt(args[0]);
        int port = 8000;
        Launcher launcher = new Launcher();
        launcher.launchGame(port);
    }


}
