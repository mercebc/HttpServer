package Handlers;

import Request.Request;
import Response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleGetHandlerTest {
    private SimpleGetHandler simpleGetHandler;
    private Response response;

    @Before
    public void SetUp(){
        simpleGetHandler = new SimpleGetHandler();
    }

    @Test
    public void responseOK() {
        String[] methods = {"HEAD", "GET"};

        for (int i = 0; i < methods.length; i++) {
            response = simpleGetHandler.respondToRequest(new Request(methods[i], "/simple_not_get", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }
    }

    @Test
    public void responseHasEmptyBody(){
        String[] methods = {"HEAD", "GET"};

        for (int i = 0; i < methods.length; i++) {
            response = simpleGetHandler.respondToRequest(new Request(methods[i], "/simple_not_get", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getBody(), is(new byte[0]));
        }
    }
    @Test
    public void responseNotAllowed() {
        String[] methods = {"POST", "PUT", "OPTIONS"};

        for (int i = 0; i < methods.length; i++) {
            response = simpleGetHandler.respondToRequest(new Request(methods[i], "/simple_not_get", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }
    }
}

