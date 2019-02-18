import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocketServerCommunicatorTest {

    private SocketCommunicator socketCommunicator;
    private PrintStream output;
    private ByteArrayInputStream input;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() {

        out = new ByteArrayOutputStream();
        output = new PrintStream(out);

        String mockInput = "Hello\nWorld";
        input = new ByteArrayInputStream(mockInput.getBytes());

        SocketStub socket = new SocketStub(input, output);

        socketCommunicator = new SocketCommunicator(socket);
    }

    @Test
    public void readsLineFromSocket() throws IOException {
        assertThat(socketCommunicator.readFromSocket(), is("Hello\nWorld"));
    }

    @Test
    public void writesToSocket() throws IOException {
        socketCommunicator.printToSocket("Hello");
        assertThat(out.toString().trim(), is("Hello"));
    }

    @Test
    public void hasMessageBeenSent() throws IOException {
        socketCommunicator.printToSocket("Hello");
        assertThat(socketCommunicator.isMessageSent(), is(true));
    }

    @Test
    public void ClosesASocket() throws IOException {
        socketCommunicator.closeConnection();
        assertThat(socketCommunicator.getSocket().isClosed(), is(true));
    }

}