package Handlers;

import Request.Request;
import Response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class MethodOptionsHandlerTest {
    private MethodOptionsHandler methodOptionsHandler;
    private Response response;

    @Before
    public void setUp() {
        methodOptionsHandler = new MethodOptionsHandler();
    }

    @Test
    public void responseStatusLineOK() {
        String[] methods = {"HEAD", "GET", "OPTIONS"};

        for (int i = 0; i < methods.length; i++) {
            response = methodOptionsHandler.respondToRequest(new Request(methods[i], "/method_options", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }
    }

    @Test
    public void getAllowedMethodsHeader(){
        response = methodOptionsHandler.respondToRequest(new Request("OPTIONS", "/method_options", "HTTP/1.1", new HashMap<>(), "Some test"));

        assertThat(response.getHeaders().get("Allow"), containsString("GET"));
        assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
        assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
    }

    @Test
    public void responseStatusLineNotAllowed() {
        String[] methods = {"PUT", "POST"};

        for (int i = 0; i < methods.length; i++) {
            response = methodOptionsHandler.respondToRequest(new Request(methods[i], "/method_options", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }
    }


}