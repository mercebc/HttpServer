package Handlers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotFoundHandlerTest {
    @Test
    public void responseNotFound() {
        NotFoundHandler notFoundHandler = new NotFoundHandler();
        assertThat(notFoundHandler.respondToRequest().getStatusLine(), is("HTTP/1.1 404 Not Found"));
    }

}