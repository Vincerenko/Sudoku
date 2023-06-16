package org.example.engine;

import org.example.modules.Cell;

public interface SudokuGameProcessor {
    String getBoardAsText();
    boolean isValidMove(Cell cell);
    void makeMove(Cell cell);

}
