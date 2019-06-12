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
public class RedirectHandlerTest {

    public static class GetMethod {
        private RedirectHandler redirectHandler;
        private Response response;

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            redirectHandler = new RedirectHandler();
            response = redirectHandler.respondToRequest(new Request("GET", "/redirect", "HTTP/1.1",  new HashMap<>(), ""));
        }

        @Test
        public void responseRedirect() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 301 Moved Permanently"));
        }

        @Test
        public void responseWithLocationHeaders(){ assertThat(response.getHeaders().get("Location"), is("http://0.0.0.0:5000/simple_get")); }

        @Test
        public void responseHasNoBody(){ assertThat(response.getBody(), is("")); }
    }

    @RunWith(value = Parameterized.class)
    public static class HttpNotAllowedMethods {
        private RedirectHandler redirectHandler;
        private Response response;
        private String method;

        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][] { {"POST"}, {"HEAD"}, {"PUT"}, {"OPTIONS"} };
            return Arrays.asList(data);
        }

        public HttpNotAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            redirectHandler = new RedirectHandler();
            response = redirectHandler.respondToRequest(new Request(method, "/redirect", "HTTP/1.1",  new HashMap<>(), ""));
        }

        @Test
        public void responseStatusLineNotAllowed() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }

    }

}