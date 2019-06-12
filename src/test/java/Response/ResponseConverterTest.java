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
        response = new Response("HTTP/1.1 200 OK");
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\n\r\n".getBytes()));
    }

    @Test
    public void convertsResponseWithEmptyBodyIntoString(){
        response = new Response("HTTP/1.1 200 OK", "".getBytes());
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\n\r\n".getBytes()));
    }

    @Test
    public void convertsResponseWithBodyIntoString(){
        response = new Response("HTTP/1.1 200 OK", "Hello world!".getBytes());
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\n\r\nHello world!".getBytes()));
    }

    @Test
    public void convertsTheResponseWithHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        response = new Response("HTTP/1.1 200 OK", headers,"Hello world!".getBytes());
        assertThat(responseConverter.responseToBytes(response), is("HTTP/1.1 200 OK\r\nfirst: example\nsecond: example2\r\nHello world!".getBytes()));
    }

    @Test
    public void convertsHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        assertThat(responseConverter.headersIntoBytes(headers), is("first: example\nsecond: example2".getBytes()));
    }

}