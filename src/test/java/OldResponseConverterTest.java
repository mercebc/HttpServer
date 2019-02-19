import Responses.OKOldResponse;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OldResponseConverterTest {
    private ResponseConverter responseConverter;
    private Response response;

    @Before
    public void Setup(){
        responseConverter = new ResponseConverter();
        response = new Response("HTTP/1.1 200 OK", "Hello world!");
    }

    @Test
    public void translatesTheResponseIntoString(){
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\n\nHello world!"));
    }


}