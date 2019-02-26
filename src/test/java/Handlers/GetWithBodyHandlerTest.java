package Handlers;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetWithBodyHandlerTest {
    private GetWithBodyHandler getWithBodyHandler;
    private Response response;
    private Response optionsResponse;

    @Before
    public void setUp() throws InvocationTargetException, IllegalAccessException {
        getWithBodyHandler = new GetWithBodyHandler();
        response = getWithBodyHandler.respondToRequest(new Request("HEAD", "/get_with_body", "HTTP/1.1",  new HashMap<>(), "Some test"));
        optionsResponse = getWithBodyHandler.respondToRequest(new Request("OPTIONS", "/get_with_body", "HTTP/1.1",  new HashMap<>(), "Some test"));
    }

    @Test
    public void responseStatusLineOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void optionsResponseStatusLineOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void getAllowedMethodsHeader(){
        assertThat(response.getHeaders().get("Allow"), is("HEAD,OPTIONS"));
    }

    @Test
    public void responseHasNoBody(){
        assertThat(response.getBody(), is(""));
    }
}