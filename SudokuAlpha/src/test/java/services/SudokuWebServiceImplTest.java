package services;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
import org.example.services.SudokuWebService;
import org.example.services.impl.SudokuWebServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.*;

class SudokuWebServiceImplTest {
    @Mock
    private HttpExchange httpExchangeMock;

    @Mock
    private OutputStream outputStreamMock;

    private SudokuWebService sudokuWebService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sudokuWebService = new SudokuWebServiceImpl();
    }

    @Test
    void testSendResponse_SendsResponseWithCorrectStatusCodeAndContentLength() throws IOException {
        String response = "Test response";
        int expectedStatusCode = 200;
        int expectedContentLength = response.length();

        when(httpExchangeMock.getResponseHeaders()).thenReturn(createResponseHeadersMock());
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);

        sudokuWebService.sendResponse(httpExchangeMock, response);

        verify(httpExchangeMock, times(1)).sendResponseHeaders(expectedStatusCode, expectedContentLength);
    }

    @Test
    void testSendResponse_WritesResponseToOutputStream() throws IOException {
        String response = "Test response";

        when(httpExchangeMock.getResponseHeaders()).thenReturn(createResponseHeadersMock());
        when(httpExchangeMock.getResponseBody()).thenReturn(outputStreamMock);

        sudokuWebService.sendResponse(httpExchangeMock, response);

        verify(outputStreamMock, times(1)).write(response.getBytes());
        verify(outputStreamMock, times(1)).close();
    }

    private static Headers createResponseHeadersMock() {
        return mock(Headers.class);
    }
}
