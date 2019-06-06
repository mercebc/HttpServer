package Handlers;

import Core.Request;
import Core.Response;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IndexHandlerTest {
    private IndexHandler indexHandler;
    private Response response;
    private Request request;

    @Before
    public void SetUp() throws InvocationTargetException, IllegalAccessException {
        indexHandler = new IndexHandler();
        request =  new Request("GET", "/","HTTP/1.1", new HashMap<>(), "");
        response = indexHandler.respondToRequest(request);
    }

    @Test
    public void responseOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void responseHasHTMLinBody(){
        String bodyExpected = "<html>\n<body>\n<p>Find out if you are going to need wellies in your town!</p>\n</body></html>\n\n";
        assertThat(response.getBody(), is(bodyExpected));
    }

}