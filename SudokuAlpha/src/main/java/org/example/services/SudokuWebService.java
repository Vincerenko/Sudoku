package org.example.services;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface SudokuWebService {
    void sendResponse(HttpExchange exchange, String response) throws IOException;
}
