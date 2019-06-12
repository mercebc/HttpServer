package Handlers;

import Request.Request;
import Response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Enclosed.class)
public class EchoBodyHandlerTest {

    public static class PostMethod {
        private EchoBodyHandler echoBodyHandler;
        private Response response;

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            echoBodyHandler = new EchoBodyHandler();
            response = echoBodyHandler.respondToRequest(new Request("POST", "/echo_body", "HTTP/1.1",  new HashMap<>(), "Some test"));
        }

        @Test
        public void responseStatusLine() { assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK")); }

        @Test
        public void responseWithContentLengthHeaders(){ assertThat(response.getHeaders().get("Content-Length"), is("9")); }

        @Test
        public void responseWithAllowedMethodsHeader(){ assertThat(response.getHeaders().get("Allow"), is("POST")); }

        @Test
        public void responseHasBody(){ assertThat(response.getBody(), is("Some test".getBytes())); }
    }

    @RunWith(value = Parameterized.class)
    public static class HttpNotAllowedMethods {
        private EchoBodyHandler echoBodyHandler;
        private Response response;
        private String method;

        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][] { {"GET"}, {"HEAD"}, {"PUT"}, {"OPTIONS"} };
            return Arrays.asList(data);
        }

        public HttpNotAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            echoBodyHandler = new EchoBodyHandler();
            response = echoBodyHandler.respondToRequest(new Request(method, "/echo_body", "HTTP/1.1",  new HashMap<>(), "Some test"));
        }

        @Test
        public void responseStatusLineNotAllowed() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }

    }

}