package org.example.config;

import com.sun.net.httpserver.HttpServer;
import org.example.engine.impl.SudokuGameProcessorImpl;
import org.example.handlers.BoardHandler;
import org.example.handlers.MoveHandler;
import org.example.services.impl.SudokuWebServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {

    public void startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/board", new BoardHandler(new SudokuGameProcessorImpl(), new SudokuWebServiceImpl()));
        server.createContext("/move", new MoveHandler(new SudokuGameProcessorImpl(), new SudokuWebServiceImpl()));
        server.setExecutor(null);
        server.start();
    }

    public void launchGame(int port) {
        try {
            startServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
