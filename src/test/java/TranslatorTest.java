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
        requestString = "GET / HTTP/1.1";
    }

    @Test
    public void createsARequestFromString(){
        Request request = translator.StringToRequest(requestString);
        assertThat(request.getVerb(), is("GET"));
    }
}