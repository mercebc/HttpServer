package Core;

import Stubs.ServerSocketStub;
import Stubs.ServerStatusStub;
import Stubs.SocketStub;
import Util.PublicFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServerTest {
    private Listener listener;
    private Router router;
    private Server server;
    private ServerStatusStub status;

    @Before
    public void Setup() {
        router = new Router();
        PublicFileReader publicFileReader = new PublicFileReader("");
        router.config(publicFileReader);

        status = new ServerStatusStub(true);
    }

    @Test
    public void SimpleGet() throws IOException, InvocationTargetException, IllegalAccessException {

        SocketStub socket = new SocketStub(new ByteArrayInputStream("GET /simple_get HTTP/1.1".getBytes()), new ByteArrayOutputStream());
        ServerSocketStub ssStub = new ServerSocketStub(socket);
        listener = new Listener(ssStub);

        server = new Server(listener, router, status);

        server.start();

        assertThat(socket.getOutputStream().toString().trim(), is("HTTP/1.1 200 OK\nAllow: GET,HEAD"));
    }

}
