package Handlers;

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
public class MethodOptionsHandlerTest {

    @RunWith(value = Parameterized.class)
    public static class HttpAllowedMethods {
        private MethodOptionsHandler methodOptionsHandler;
        private Response response;
        private String method;

        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][] { {"HEAD"}, {"GET"}, {"OPTIONS"} };
            return Arrays.asList(data);
        }

        public HttpAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            methodOptionsHandler = new MethodOptionsHandler();
            response = methodOptionsHandler.respondToRequest(new Request(method, "/method_options", "HTTP/1.1",  new HashMap<>(), "Some test"));
        }

        @Test
        public void responseStatusLineOK() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }

        @Test
        public void getAllowedMethodsHeader(){
            assertThat(response.getHeaders().get("Allow"), containsString("GET"));
            assertThat(response.getHeaders().get("Allow"), containsString("OPTIONS"));
            assertThat(response.getHeaders().get("Allow"), containsString("HEAD"));
        }
    }

    @RunWith(value = Parameterized.class)
    public static class HttpNotAllowedMethods {
        private MethodOptionsHandler methodOptionsHandler;
        private Response response;
        private String method;

        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][] { {"PUT"}, {"POST"} };
            return Arrays.asList(data);
        }

        public HttpNotAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void setUp() throws InvocationTargetException, IllegalAccessException {
            methodOptionsHandler = new MethodOptionsHandler();
            response = methodOptionsHandler.respondToRequest(new Request(method, "/method_options", "HTTP/1.1",  new HashMap<>(), "Some test"));
        }

        @Test
        public void responseStatusLineNotAllowed() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }

    }

}