package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.engine.SudokuGameProcessor;
import org.example.services.SudokuWebService;

import java.io.IOException;

@Data
@AllArgsConstructor
public class BoardHandler implements HttpHandler {
    private SudokuGameProcessor sudokuGameProcessor;
    private SudokuWebService sudokuWebService;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = sudokuGameProcessor.getBoardAsText();
        sudokuWebService.sendResponse(exchange, response);
    }
}