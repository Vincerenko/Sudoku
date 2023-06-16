package handlers;

import com.sun.net.httpserver.HttpExchange;
import org.example.engine.SudokuGameProcessor;
import org.example.handlers.MoveHandler;
import org.example.modules.Cell;
import org.example.services.SudokuWebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class MoveHandlerTest {
    @Mock
    private SudokuGameProcessor sudokuGameProcessorMock;

    @Mock
    private SudokuWebService sudokuWebServiceMock;

    @Mock
    private HttpExchange httpExchangeMock;

    private MoveHandler moveHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        moveHandler = new MoveHandler(sudokuGameProcessorMock, sudokuWebServiceMock);
    }

    @Test
    void testHandle_ValidRequest_CallsMakeMoveAndSendResponse() throws IOException {
        String query = "row=2&col=3&value=5";
        Cell cell = new Cell();
        cell.setRow(1);
        cell.setCol(2);
        cell.setValue(5);

        when(httpExchangeMock.getRequestURI()).thenReturn(createUriWithQuery(query));
        when(sudokuGameProcessorMock.isValidMove(cell)).thenReturn(true);

        moveHandler.handle(httpExchangeMock);

        verify(sudokuGameProcessorMock, times(1)).makeMove(cell);
        verify(sudokuWebServiceMock, times(1)).sendResponse(httpExchangeMock, "Valid move. Value added successfully!");
    }

    @Test
    void testHandle_InvalidRequest_CallsSendResponseWithInvalidRequestMessage() throws IOException {
        String query = "row=-1&col=3&value=10";

        when(httpExchangeMock.getRequestURI()).thenReturn(createUriWithQuery(query));

        moveHandler.handle(httpExchangeMock);

        verify(sudokuWebServiceMock, times(1)).sendResponse(httpExchangeMock, "Invalid request. Please provide valid parameters.");
    }

    @Test
    void testHandle_InvalidMove_CallsSendResponseWithInvalidMoveMessage() throws IOException {
        String query = "row=5&col=4&value=2";
        Cell cell = new Cell();
        cell.setRow(4);
        cell.setCol(3);
        cell.setValue(2);

        when(httpExchangeMock.getRequestURI()).thenReturn(createUriWithQuery(query));
        when(sudokuGameProcessorMock.isValidMove(cell)).thenReturn(false);

        moveHandler.handle(httpExchangeMock);

        verify(sudokuWebServiceMock, times(1)).sendResponse(httpExchangeMock, "Invalid move. Try again.");
    }

    private static java.net.URI createUriWithQuery(String query) {
        return java.net.URI.create("/move?" + query);
    }
}
