package org.example.engine.impl;

import org.example.engine.SudokuGameProcessor;
import org.example.modules.Cell;

import java.security.SecureRandom;

public class SudokuGameProcessorImpl implements SudokuGameProcessor {
    int quantityOfCells = 9;
    private int[][] board;

    public SudokuGameProcessorImpl() {
        board = new int[quantityOfCells][quantityOfCells];
        generateRandomBoard();
    }

    @Override
    public String getBoardAsText() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sb.append(board[row][col]);
                if (col < 8) {
                    sb.append(" | ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public boolean isValidMove(Cell cell) {
        if (isValuesCorrect(cell) && isValueNotEqualsToZero(cell) && checkRowAndColumn(cell) && checkGrid(cell))
            return false;
        return true;
    }

    @Override
    public void makeMove(Cell cell) {
        board[cell.getRow()][cell.getCol()] = cell.getValue();
    }

    private static boolean isValuesCorrect(Cell cell) {
        if (cell.getRow() < 0 || cell.getRow() >= 9 || cell.getCol() < 0 || cell.getCol() >= 9 || cell.getValue() < 1 || cell.getValue() > 9) {
            return true;
        }
        return false;
    }

    private boolean isValueNotEqualsToZero(Cell cell) {
        if (board[cell.getRow()][cell.getCol()] != 0) {
            return true;
        }
        return false;
    }

    private boolean checkRowAndColumn(Cell cell) {
        for (int i = 0; i < 9; i++) {
            if (board[cell.getRow()][i] == cell.getValue() || board[i][cell.getCol()] == cell.getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkGrid(Cell cell) {
        int startRow = cell.getRow() - cell.getRow() % 3;
        int startCol = cell.getCol() - cell.getCol() % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == cell.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }


    private void generateRandomBoard() {
        SecureRandom random = new SecureRandom();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (random.nextDouble() <= 0.15) {
                    board[row][col] = random.nextInt(quantityOfCells);
                }
            }
        }
    }

}
