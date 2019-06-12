package Handlers;

import Request.Request;
import Response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RedirectHandlerTest {
    private RedirectHandler redirectHandler;
    private Response response;

    @Before
    public void setUp() {
        redirectHandler = new RedirectHandler();
    }

    @Test
    public void responseRedirect() {
        response = redirectHandler.respondToRequest(new Request("GET", "/redirect", "HTTP/1.1", new HashMap<>(), ""));

        assertThat(response.getStatusLine(), is("HTTP/1.1 301 Moved Permanently"));
    }


    @Test
    public void responseWithLocationHeaders(){
        response = redirectHandler.respondToRequest(new Request("GET", "/redirect", "HTTP/1.1", new HashMap<>(), ""));

        assertThat(response.getHeaders().get("Location"), is("http://0.0.0.0:5000/simple_get"));
    }

    @Test
    public void responseHasNoBody(){
        response = redirectHandler.respondToRequest(new Request("GET", "/redirect", "HTTP/1.1", new HashMap<>(), ""));

        assertThat(response.getBody(), is(new byte[0]));
    }


    @Test
    public void responseStatusLineNotAllowed() {
        String[] methods = {"HEAD", "OPTIONS", "PUT", "POST"};

        for (int i = 0; i < methods.length; i++) {
            response = redirectHandler.respondToRequest(new Request(methods[i], "/redirect", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }
    }

}
