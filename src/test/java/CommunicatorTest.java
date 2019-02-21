import Stubs.SocketStub;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CommunicatorTest {
    private SocketStub socket;
    private SocketHelper ss;
    private Communicator com;

    @Before
    public void Setup(){
        socket = new SocketStub(new ByteArrayInputStream("GET /simple_get HTTP/1.1".getBytes()), new ByteArrayOutputStream());
        ss = new SocketHelper(socket);
        com = new Communicator(ss);
    }

    @Test
     public void ReceivesARequestString() throws IOException {
        com.getRequest();
        assertThat(com.getRequestString(), is("GET /simple_get HTTP/1.1"));
    }
}