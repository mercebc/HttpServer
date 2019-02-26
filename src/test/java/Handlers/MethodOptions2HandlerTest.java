package Handlers;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MethodOptions2HandlerTest {
    private MethodOptions2Handler methodOptions2Handler;
    private Response response;

    @Before
    public void setUp() throws InvocationTargetException, IllegalAccessException {
        methodOptions2Handler = new MethodOptions2Handler();
        response = methodOptions2Handler.respondToRequest(new Request("OPTIONS", "/method_options2", "HTTP/1.1",  new HashMap<>(), "Some test"));
    }

    @Test
    public void responseStatusLineOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void getAllowedMethodsHeader(){
        assertThat(response.getHeaders().get("Allow"), containsString("GET"));
        assertThat(response.getHeaders().get("Allow"), containsString("POST"));
        assertThat(response.getHeaders().get("Allow"), containsString("PUT"));
        assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
        assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
    }
}