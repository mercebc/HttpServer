import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListenerTest {
    Listener listener;
    Socket socket;

    @Before
    public void Setup() throws IOException {
        socket = new SocketStub(new ByteArrayInputStream("hello".getBytes()), new ByteArrayOutputStream());
        listener = new Listener(new ServerSocketStub(socket));
    }

    @Test
    public void createsAStreamSocket() throws IOException {
        assertThat(listener.connect(), is(StreamSocket.class));
    }

}