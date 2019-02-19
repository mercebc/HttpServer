import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OKResponseHandlerTest {

    @Test
    public void responseOK() {
        OKResponseHandler okResponseHandler = new OKResponseHandler();
        assertThat(okResponseHandler.respondToRequest().getHeaders(), is("HTTP/1.1 200 OK"));
    }
}