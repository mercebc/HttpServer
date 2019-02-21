package Handlers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RedirectHandlerTest {
    private RedirectHandler redirectHandler;

    @Test
    public void addsHeaders(){
        redirectHandler = new RedirectHandler();
        Response response = redirectHandler.respondToRequest();
        assertThat(response.getHeaders().get("Location"), is("http://0.0.0.0:5000/simple_get"));
    }

}