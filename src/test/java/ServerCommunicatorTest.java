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
        socket = new SocketStub(new ByteArrayInputStream("hello world http".getBytes()), new ByteArrayOutputStream());
        ss = new SocketCommunicator(socket);
        com = new ServerCommunicator(ss);
    }

    @Test
     public void ReceivesARequestString() throws IOException {
        com.run();
        assertThat(com.getRequestString(), is("hello world http"));
    }
}