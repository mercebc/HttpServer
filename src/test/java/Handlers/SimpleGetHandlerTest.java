package Handlers;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleGetHandlerTest {
    private SimpleGetHandler simpleGetHandler;
    private Response response;

    @Before
    public void SetUp(){
        simpleGetHandler = new SimpleGetHandler();
        response = simpleGetHandler.respondToRequest();
    }

    @Test
    public void responseOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseHasEmptyBody(){
        assertThat(response.getBody(), is(""));
    }
}