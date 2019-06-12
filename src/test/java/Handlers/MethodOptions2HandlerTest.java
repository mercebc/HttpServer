package Handlers;

import Request.Request;
import Response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MethodOptions2HandlerTest {
    private MethodOptions2Handler methodOptions2Handler;
    private Response response;
    private String method;

    @Before
    public void setUp(){
        methodOptions2Handler = new MethodOptions2Handler();
    }

    @Test
    public void responseStatusLineOK() {
        String[] methods = {"HEAD", "GET", "PUT", "POST"};

        for (int i = 0; i < methods.length; i++) {
            response = methodOptions2Handler.respondToRequest(new Request(methods[i], "/method_options2", "HTTP/1.1", new HashMap<>(), "Some test"));
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }
    }

    @Test
    public void getAllowedMethodsHeader(){
        response = methodOptions2Handler.respondToRequest(new Request("OPTIONS", "/method_options2", "HTTP/1.1", new HashMap<>(), "Some test"));

        assertThat(response.getHeaders().get("Allow"), containsString("GET"));
        assertThat(response.getHeaders().get("Allow"), containsString("POST"));
        assertThat(response.getHeaders().get("Allow"), containsString("PUT"));
        assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
        assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
    }
}