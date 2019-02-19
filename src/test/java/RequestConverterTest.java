import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RequestConverterTest {
    private RequestConverter requestConverter;
    private String requestString;

    @Before
    public void Setup() {
        requestConverter = new RequestConverter();
        requestString = "GET /simple_request HTTP/1.1";
    }

    @Test
    public void getsTheVerbOfARequest() {
        Request request = requestConverter.stringToRequest(requestString);
        assertThat(request.getVerb(), is("GET"));
    }

    @Test
    public void getsTheURIOfARequest() {
        Request request = requestConverter.stringToRequest(requestString);
        assertThat(request.getURI(), is("/simple_request"));
    }

    @Test
    public void getsTheProtocolOfARequest() {
        Request request = requestConverter.stringToRequest(requestString);
        assertThat(request.getProtocol(), is("HTTP/1.1"));
    }
}