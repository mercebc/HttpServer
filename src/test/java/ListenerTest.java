import Stubs.ServerSocketStub;
import Stubs.SocketStub;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServerListenerTest {
    private ServerListener serverListener;
    private Socket socket;
    private ServerSocketStub sstub;

    @Before
    public void Setup() throws IOException {
        socket = new SocketStub(new ByteArrayInputStream("hello".getBytes()), new ByteArrayOutputStream());
        sstub = new ServerSocketStub(socket);
        serverListener = new ServerListener(sstub);
    }

    @Test
    public void createsAStreamSocket() throws IOException {
        assertThat(serverListener.connect(), is(SocketHelper.class));
    }

    @Test
    public void acceptsAConnection() throws IOException {
        serverListener.connect();
        assertThat(sstub.hasAcceptBeenCalled(), is(true));
    }

}