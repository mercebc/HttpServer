import Stubs.SocketStub;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocketIOTest {

    private SocketIO socketIO;
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

        socketIO = new SocketIO(socket);
    }

    @Test
    public void readsLineFromSocket() throws IOException {
        assertThat(socketIO.readFromSocket(), is("Hello\nWorld"));
    }

    @Test
    public void writesToSocket() throws IOException {
        socketIO.printToSocket("Hello");
        assertThat(out.toString().trim(), is("Hello"));
    }

    @Test
    public void hasMessageBeenSent() throws IOException {
        socketIO.printToSocket("Hello");
        assertThat(socketIO.isMessageSent(), is(true));
    }

    @Test
    public void ClosesASocket() throws IOException {
        socketIO.closeConnection();
        assertThat(socketIO.getSocket().isClosed(), is(true));
    }

}