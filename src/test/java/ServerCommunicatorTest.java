import Stubs.SocketStub;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ServerCommunicatorTest {
    private SocketStub socket;
    private SocketCommunicator ss;
    private ServerCommunicator com;

    @Before
    public void Setup(){
        socket = new SocketStub(new ByteArrayInputStream("GET /simple_get HTTP/1.1".getBytes()), new ByteArrayOutputStream());
        ss = new SocketCommunicator(socket);
        com = new ServerCommunicator(ss);
    }

    @Test
     public void ReceivesARequestString() throws IOException {
        com.handleRequest();
        assertThat(com.getRequestString(), is("GET /simple_get http"));
    }
}