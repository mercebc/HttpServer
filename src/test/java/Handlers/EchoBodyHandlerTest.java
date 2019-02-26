package Handlers;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EchoBodyHandlerTest {
    private EchoBodyHandler echoBodyHandler;
    private Response response;

    @Before
    public void setUp() throws InvocationTargetException, IllegalAccessException {
        echoBodyHandler = new EchoBodyHandler();
        response = echoBodyHandler.respondToRequest(new Request("POST", "/echo_body", "HTTP/1.1",  new HashMap<>(), "Some test"));
    }

    @Test
    public void responseStatusLine() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseWithContentLengthHeaders(){
        assertThat(response.getHeaders().get("Content-Length"), is("9"));
    }

    @Test
    public void responseWithAllowedMethodsHeader(){
        assertThat(response.getHeaders().get("Allow"), is("POST"));
    }

    @Test
    public void responseHasBody(){
        assertThat(response.getBody(), is("Some test"));
    }
}