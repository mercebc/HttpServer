import Handlers.Request;
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
        assertThat(request.getMethod(), is("GET"));
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

    @Test
    public void convertsIntoRequestWithHeadersAndBody(){
        requestString = "GET /simple_request HTTP/1.1\r\nexample: header\r\n\r\nsome body";
        Request request = requestConverter.stringToRequest(requestString);
        assertThat(request.getMessageBody(), is("some body"));
    }

    @Test
    public void convertsIntoRequestWithOneHeader(){
        requestString = "GET /simple_request HTTP/1.1\r\nexample: header\r\n\r\n";
        Request request = requestConverter.stringToRequest(requestString);
        assertThat(request.getHeaders().get("example"), is("header"));
    }

    @Test
    public void convertsIntoRequestWithMultipleHeaders(){
        requestString = "GET /redirect HTTP/1.1\r\n" +
                "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\r\n" +
                "Accept: */*\r\n" +
                "User-Agent: Ruby\r\n" +
                "Connection: close\r\n" +
                "Host: 0.0.0.0:5000\r\n" +
                "\r\n" +
                "\r\n";

        Request request = requestConverter.stringToRequest(requestString);
        assertThat(request.getHeaders().get("Host"), is("0.0.0.0:5000"));
    }

}