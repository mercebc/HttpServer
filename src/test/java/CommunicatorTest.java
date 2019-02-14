import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CommunicatorTest {
    private SocketStub socket;
    private StreamSocket ss;
    private Communicator com;

    @Before
    public void Setup(){
        socket = new SocketStub(new ByteArrayInputStream("hello".getBytes()), new ByteArrayOutputStream());
        ss = new StreamSocket(socket.getInputStream(), socket.getOutputStream());
        com = new Communicator(ss);
    }

    @Test
     public void ReceivesARequestString() throws IOException {
        com.run();
        assertThat(com.getRequestString(), is("hello"));
    }
}