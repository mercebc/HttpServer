package Handlers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = Parameterized.class)
public class MethodOptions2HandlerTest {
    private MethodOptions2Handler methodOptions2Handler;
    private Response response;
    private String method;

    @Parameterized.Parameters
    public static Collection data() {
        Object[][] data = new Object[][] { {"HEAD"}, {"GET"},{"PUT"},{"POST"}, {"OPTIONS"} };
        return Arrays.asList(data);
    }

    public MethodOptions2HandlerTest(String method) {
        this.method = method;
    }

    @Before
    public void setUp() throws InvocationTargetException, IllegalAccessException {
        methodOptions2Handler = new MethodOptions2Handler();
        response = methodOptions2Handler.respondToRequest(new Request(method, "/method_options2", "HTTP/1.1",  new HashMap<>(), "Some test"));
    }

    @Test
    public void responseStatusLineOK() {
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void getAllowedMethodsHeader(){
        assertThat(response.getHeaders().get("Allow"), containsString("GET"));
        assertThat(response.getHeaders().get("Allow"), containsString("POST"));
        assertThat(response.getHeaders().get("Allow"), containsString("PUT"));
        assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
        assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
    }
}