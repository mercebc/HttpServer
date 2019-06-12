package Response;

import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResponseConverterTest {
    private ResponseConverter responseConverter;
    private Response response;

    @Before
    public void Setup(){
        responseConverter = new ResponseConverter();
    }

    @Test
    public void convertsResponseWithNoBodyIntoString(){
        response = Response.ok();
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\n\r\n".getBytes()));
    }

    @Test
    public void convertsResponseWithEmptyBodyIntoString(){
        response = Response.ok().withBody("".getBytes());
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n".getBytes()));
    }

    @Test
    public void convertsResponseWithBodyIntoString(){
        response = Response.ok().withBody("Hello world!".getBytes());
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\nContent-Length: 12\r\n\r\nHello world!".getBytes()));
    }

    @Test
    public void convertsTheResponseWithHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        response = Response.ok().withHeaders(headers).withBody("Hello world!".getBytes());
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\nContent-Length: 12\r\nfirst: example\r\nsecond: example2\r\n\r\nHello world!".getBytes()));
    }

    @Test
    public void convertsHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        assertThat(responseConverter.headersIntoBytes(headers), is("first: example\r\nsecond: example2\r\n".getBytes()));
    }

}