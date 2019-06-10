package Handlers;

import Core.Request;
import Core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Enclosed.class)
public class GetWithBodyHandlerTest {

    @RunWith(value = Parameterized.class)
    public static class HttpAllowedMethods {
        private GetWithBodyHandler getWithBodyHandler;
        private Response response;
        private String method;

        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][]{{"HEAD"}, {"OPTIONS"}};
            return Arrays.asList(data);
        }

        public HttpAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            getWithBodyHandler = new GetWithBodyHandler();
            response = getWithBodyHandler.respondToRequest(new Request(method, "/get_with_body", "HTTP/1.1", new HashMap<>(), "Some test"));
        }

        @Test
        public void responseStatusLineOK() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }

        @Test
        public void getAllowedMethodsHeader() {
            assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
            assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
        }

        @Test
        public void responseHasNoBody() {
            assertThat(response.getBody(), is(""));
        }

    }

    @RunWith(value = Parameterized.class)
    public static class HttpNotAllowedMethods {
        private GetWithBodyHandler getWithBodyHandler;
        private Response response;
        private String method;

        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][] { {"GET"}, {"PUT"}, {"POST"} };
            return Arrays.asList(data);
        }

        public HttpNotAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            getWithBodyHandler = new GetWithBodyHandler();
            response = getWithBodyHandler.respondToRequest(new Request(method, "/get_with_body", "HTTP/1.1",  new HashMap<>(), "Some test"));
        }

        @Test
        public void responseStatusLineNotAllowed() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }

    }

}