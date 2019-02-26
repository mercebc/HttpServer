package Handlers;

import org.junit.Before;
import org.junit.Test;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleGetHandlerTest {
    private SimpleGetHandler simpleGetHandler;
    private Response response;
    private Request request = new Request("GET", "/simple_not_get","HTTP/1.1", new HashMap<>(), "");
    private Response notAllowedResponse;
    private Request notAllowedRequest = new Request("OPTIONS", "/simple_not_get","HTTP/1.1", new HashMap<>(), "");


    @Before
    public void SetUp() throws InvocationTargetException, IllegalAccessException {
        simpleGetHandler = new SimpleGetHandler();
        response = simpleGetHandler.respondToRequest(request);
        notAllowedResponse = simpleGetHandler.respondToRequest(notAllowedRequest);
    }

    @Test
    public void responseOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseNotAllowed(){
        assertThat(notAllowedResponse.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
    }

    @Test
    public void responseHasEmptyBody(){
        assertThat(response.getBody(), is(""));
    }
}