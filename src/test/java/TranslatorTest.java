import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TranslatorTest {
    private Translator translator;
    private String requestString;

    @Before
    public void Setup(){
        translator = new Translator();
        requestString = "GET /simple_request HTTP/1.1";
    }

    @Test
    public void getsTheVerbOfARequest(){
        Request request = translator.stringToRequest(requestString);
        assertThat(request.getVerb(), is("GET"));
    }

    @Test
    public void getsTheURIOfARequest(){
        Request request = translator.stringToRequest(requestString);
        assertThat(request.getURI(), is("/simple_request"));
    }

    @Test
    public void getsTheProtocolOfARequest(){
        Request request = translator.stringToRequest(requestString);
        assertThat(request.getProtocol(), is("HTTP/1.1"));
    }

    @Test
    public void translatesTheResponseIntoString(){
        Response response = new Response();
        response.setStatus("OK");
        response.setCode(200);
        response.setBody("Hello world!");
        assertThat(translator.responseToString(response), is("HTTP/1.1 200 OK\n\nHello world!"));
    }


}