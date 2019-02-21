import Handlers.Response;
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
        response = new Response("HTTP/1.1 200 OK", "Hello world!");
    }

    @Test
    public void convertsTheResponseIntoString(){
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\n\nHello world!"));
    }

    @Test
    public void convertsTheResponseWithHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        Response myResponse = new Response("HTTP/1.1 200 OK", headers, "Hello world!");
        assertThat(responseConverter.responseToString(myResponse), is("HTTP/1.1 200 OK\nfirst: example\nsecond: example2\n\nHello world!"));
    }

    @Test
    public void convertsHeadersIntoString(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("first", "example");
        headers.put("second", "example2");
        assertThat(responseConverter.headersIntoString(headers), is("first: example\nsecond: example2\n"));
    }

}