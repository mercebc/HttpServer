package Handlers;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RedirectHandlerTest {
    private RedirectHandler redirectHandler;
    private Response response;

    @Before
    public void setUp(){
        redirectHandler = new RedirectHandler();
        response = redirectHandler.respondToRequest();
    }

    @Test
    public void responseRedirect() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 301 Moved Permanently"));
    }

    @Test
    public void responseWithLocationHeaders(){
        assertThat(response.getHeaders().get("Location"), is("http://0.0.0.0:5000/simple_get"));
    }

    @Test
    public void responseHasNoBody(){
        assertThat(response.getBody(), is(""));
    }

}