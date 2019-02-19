import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResponseConverterTest {
    private ResponseConverter responseConverter;
    private Response response;

    @Before
    public void Setup(){
        responseConverter = new ResponseConverter();
        response = new Response();
        response.setStatus("OK");
        response.setCode(200);
        response.setBody("Hello world!");
    }

    @Test
    public void translatesTheResponseIntoString(){
        assertThat(responseConverter.responseToString(response), is("HTTP/1.1 200 OK\n\nHello world!"));
    }


}