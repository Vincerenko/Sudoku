package org.example.modules.enums;

public enum Grid {
    ROW("row"), COL("col"), VALUE("value");

    private final String title;

    Grid(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "title='" + title + '\'' +
                '}';
    }
}
