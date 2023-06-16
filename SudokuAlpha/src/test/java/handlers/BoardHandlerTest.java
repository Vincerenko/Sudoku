package handlers;

import com.sun.net.httpserver.HttpExchange;
import org.example.engine.SudokuGameProcessor;
import org.example.handlers.BoardHandler;
import org.example.services.SudokuWebService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class BoardHandlerTest {
    @Mock
    private SudokuGameProcessor sudokuGameProcessorMock;

    @Mock
    private SudokuWebService sudokuWebServiceMock;

    @Mock
    private HttpExchange httpExchangeMock;

    @Test
    void testHandle_CallsGetBoardAsTextAndSendResponse() throws IOException {
        MockitoAnnotations.openMocks(this);

        BoardHandler boardHandler = new BoardHandler(sudokuGameProcessorMock, sudokuWebServiceMock);

        String boardText = "1 | 2 | 3\n4 | 5 | 6\n7 | 8 | 9\n";
        when(sudokuGameProcessorMock.getBoardAsText()).thenReturn(boardText);

        boardHandler.handle(httpExchangeMock);

        verify(sudokuGameProcessorMock, times(1)).getBoardAsText();
        verify(sudokuWebServiceMock, times(1)).sendResponse(httpExchangeMock, boardText);
    }
}
