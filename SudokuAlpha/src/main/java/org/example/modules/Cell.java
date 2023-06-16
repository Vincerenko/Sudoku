package org.example.modules;

import lombok.Data;

@Data
public class Cell {
    private int row;
    private int col;
    private int value;

    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public Cell() {
        this.row = -1;
        this.col = -1;
        this.value = -1;
    }
}
