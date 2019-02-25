import Handlers.Response;
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
     public void ReceivesARequest() throws IOException {
        Request request = com.getRequest();
        String requestLine = String.join(" ", request.getRequestLine());
        assertThat(requestLine, is("GET /simple_get HTTP/1.1"));
    }

    @Test
    public void sendsAResponse() throws IOException {
        Response response = new Response("HTTP/1.1 404 Not Found");
        com.sendResponse(response);
        assertThat(socket.getOutputStream().toString().trim(), is("HTTP/1.1 404 Not Found"));

    }
}