import Stubs.SocketStub;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocketServerCommunicatorTest {

    private SocketHelper socketHelper;
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

        socketHelper = new SocketHelper(socket);
    }

    @Test
    public void readsLineFromSocket() throws IOException {
        assertThat(socketHelper.readFromSocket(), is("Hello\nWorld"));
    }

    @Test
    public void writesToSocket() throws IOException {
        socketHelper.printToSocket("Hello");
        assertThat(out.toString().trim(), is("Hello"));
    }

    @Test
    public void hasMessageBeenSent() throws IOException {
        socketHelper.printToSocket("Hello");
        assertThat(socketHelper.isMessageSent(), is(true));
    }

    @Test
    public void ClosesASocket() throws IOException {
        socketHelper.closeConnection();
        assertThat(socketHelper.getSocket().isClosed(), is(true));
    }

}