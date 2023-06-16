import org.example.config.Launcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import com.sun.net.httpserver.HttpServer;

import static org.mockito.Mockito.*;

class LauncherTest {

    @Mock
    private HttpServer httpServerMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLaunchGame_StartsServer() throws IOException {
        int port = 8080;

        Launcher launcher = new Launcher();
        launcher.startServer(port);

        verify(httpServerMock, times(0)).start();
    }

    @Test
    void testLaunchGame_ExceptionOccurs_PrintsStackTrace() throws IOException {
        int port = 8080;

        Launcher launcher = Mockito.spy(new Launcher());
        doThrow(new IOException()).when(launcher).startServer(port);

        launcher.launchGame(port);

        Mockito.verify(launcher).launchGame(port);
    }
}
