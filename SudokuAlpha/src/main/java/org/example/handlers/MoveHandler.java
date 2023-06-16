package org.example.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.engine.SudokuGameProcessor;
import org.example.modules.Cell;
import org.example.services.SudokuWebService;

import java.io.IOException;

import static org.example.modules.enums.Grid.*;

@Data
@AllArgsConstructor
public class MoveHandler implements HttpHandler {

    private SudokuGameProcessor sudokuGameProcessor;
    private SudokuWebService sudokuWebService;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String[] params = query.split("&");
        Cell cell = new Cell();

        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String val = keyValue[1];
            if (key.equals(ROW.getTitle())) {
                cell.setRow(Integer.parseInt(val) - 1);
            } else if (key.equals(COL.getTitle())) {
                cell.setCol(Integer.parseInt(val) - 1);
            } else if (key.equals(VALUE.getTitle())) {
                cell.setValue(Integer.parseInt(val));
            }
        }
        collectResponse(exchange, cell);
    }

    private void collectResponse(HttpExchange exchange, Cell cell) throws IOException {
        String response;
        if (cell.getRow() >= 0 && cell.getCol() >= 0 && cell.getValue() >= 1 && cell.getValue() <= 9) {
            if (sudokuGameProcessor.isValidMove(cell)) {
                sudokuGameProcessor.makeMove(cell);
                response = "Valid move. Value added successfully!";
            } else {
                response = "Invalid move. Try again.";
            }
        } else {
            response = "Invalid request. Please provide valid parameters.";
        }

        sudokuWebService.sendResponse(exchange, response);
    }
}