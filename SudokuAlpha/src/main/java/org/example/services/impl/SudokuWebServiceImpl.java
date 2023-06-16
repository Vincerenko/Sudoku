package org.example.services.impl;

import com.sun.net.httpserver.HttpExchange;
import lombok.Data;
import org.example.services.SudokuWebService;

import java.io.IOException;
import java.io.OutputStream;

@Data
public class SudokuWebServiceImpl implements SudokuWebService {

    @Override
    public void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }


}
