import Handlers.SimpleGetHandler;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleGetHandlerTest {

    @Test
    public void responseOK() {
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();
        assertThat(simpleGetHandler.respondToRequest().getStatusLine(), is("HTTP/1.1 200 OK"));
    }
}