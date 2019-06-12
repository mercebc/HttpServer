package Response;

import Response.Response;
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
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\n\r\n"));
    }

    @Test
    public void convertsResponseWithEmptyBodyIntoString(){
        response = new Response("HTTP/1.1 200 OK", "");
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\n\r\n"));
    }

    @Test
    public void convertsResponseWithBodyIntoString(){
        response = new Response("HTTP/1.1 200 OK", "Hello world!");
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\n\r\nHello world!"));
    }

    @Test
    public void convertsTheResponseWithHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        response = new Response("HTTP/1.1 200 OK", headers,"Hello world!");
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\nfirst: example\nsecond: example2\r\n\r\nHello world!"));
    }

    @Test
    public void convertsHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        assertThat(responseConverter.headersIntoString(headers), is("first: example\nsecond: example2"));
    }

}