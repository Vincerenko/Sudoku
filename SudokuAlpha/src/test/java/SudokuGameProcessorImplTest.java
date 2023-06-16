import org.example.engine.SudokuGameProcessor;
import org.example.engine.impl.SudokuGameProcessorImpl;
import org.example.modules.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGameProcessorImplTest {
    private SudokuGameProcessor sudokuGameProcessor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        sudokuGameProcessor = new SudokuGameProcessorImpl();
    }

    @Test
    void testGetBoardAsText_ReturnsNonEmptyString() {
        String boardText = sudokuGameProcessor.getBoardAsText();
        assertNotNull(boardText);
        assertTrue(boardText.length() > 0);
    }

    @Test
    void testIsValidMove_ValidCell_ReturnsTrue() {
        Cell cell = new Cell(0, 0, 1);
        boolean isValidMove = sudokuGameProcessor.isValidMove(cell);
        assertTrue(isValidMove);
    }

    @Test
    void testIsValidMove_InvalidCell_ReturnsFalse() {
        Cell cell = new Cell(0, 0, 0);
        boolean isValidMove = sudokuGameProcessor.isValidMove(cell);
        assertFalse(isValidMove);
    }

    @Test
    void testMakeMove_UpdatesBoard() {
        Cell cell = new Cell(0, 0, 1);
        sudokuGameProcessor.makeMove(cell);
        String boardText = sudokuGameProcessor.getBoardAsText();
        assertTrue(boardText.contains("1"));
    }
}

