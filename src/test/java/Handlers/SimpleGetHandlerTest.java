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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Enclosed.class)
public class SimpleGetHandlerTest {

    @RunWith(Parameterized.class)
    public static class HttpAllowedMethods{
        private SimpleGetHandler simpleGetHandler;
        private Response response;
        private Request request;
        private String method;


        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][] { {"HEAD"}, {"GET"} };
            return Arrays.asList(data);
        }

        public HttpAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void SetUp() throws InvocationTargetException, IllegalAccessException {
            simpleGetHandler = new SimpleGetHandler();
            request =  new Request(method, "/simple_not_get","HTTP/1.1", new HashMap<>(), "");
            response = simpleGetHandler.respondToRequest(request);
        }

        @Test
        public void responseOK() {
            assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        }

        @Test
        public void responseHasEmptyBody(){
            assertThat(response.getBody(), is(""));
        }

    }

    @RunWith(Parameterized.class)
    public static class HttpNotAllowedMethods {
        private SimpleGetHandler simpleGetHandler;
        private Response response;
        private Request request;
        private String method;


        @Parameterized.Parameters
        public static Collection data() {
            Object[][] data = new Object[][]{{"POST"}, {"PUT"}, {"OPTIONS"}};
            return Arrays.asList(data);
        }

        public HttpNotAllowedMethods(String method) {
            this.method = method;
        }

        @Before
        public void SetUp() throws InvocationTargetException, IllegalAccessException {
            simpleGetHandler = new SimpleGetHandler();
            request =  new Request(method, "/simple_not_get","HTTP/1.1", new HashMap<>(), "");
            response = simpleGetHandler.respondToRequest(request);
        }

        @Test
        public void responseNotAllowed(){
            assertThat(response.getStatusLine(), is("HTTP/1.1 405 Method Not Allowed"));
        }
    }

}