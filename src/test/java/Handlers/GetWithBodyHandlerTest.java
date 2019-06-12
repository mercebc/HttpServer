package Handlers;

import Request.Request;
import Response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetWithBodyHandlerTest {
    private GetWithBodyHandler getWithBodyHandler;
    private Response response;
    private String method;


    @Before
    public void setUp() {
        getWithBodyHandler = new GetWithBodyHandler();
    }

    @Test
    public void responseStatusLineOK() {
        String[] methods = {"HEAD", "OPTIONS"};

        for (int i = 0; i < methods.length; i++) {
            response = getWithBodyHandler.respondToRequest(new Request(methods[i], "/get_with_body", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }
    }

    @Test
    public void getAllowedMethodsHeader() {
        response = getWithBodyHandler.respondToRequest(new Request("OPTIONS", "/get_with_body", "HTTP/1.1", new HashMap<>(), "Some test"));

        assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
        assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
    }

    @Test
    public void responseHasNoBody() {
        String[] methods = {"HEAD", "OPTIONS"};

        for (int i = 0; i < methods.length; i++) {
            response = getWithBodyHandler.respondToRequest(new Request(methods[i], "/get_with_body", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getBody(), is(new byte[0]));
        }
    }

    @Test
    public void responseStatusLineNotAllowed() {
        String[] methods = {"GET", "PUT", "POST"};

        for (int i = 0; i < methods.length; i++) {
            response = getWithBodyHandler.respondToRequest(new Request(methods[i], "/get_with_body", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }
    }


}