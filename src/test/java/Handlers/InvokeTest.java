package Handlers;

import Response.Response;
import Request.Request;
import Stubs.MethodNotAllowedExceptionStub;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InvokeTest {

    @Test
    public void invokesAEchoBodyMethod(){
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();
        Method[] method = EchoBodyHandler.class.getDeclaredMethods();

        Invoke invoke = new Invoke(echoBodyHandler, method[0]);
        Response response = invoke.toRespondTo(new Request(method[0].getName(), "/echo_body", "HTTP/1.1",  new HashMap<>(), "Some test"));

        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
        assertThat(response.getBody(), is("Some test".getBytes()));
    }

    @Test
    public void invokesASimpleGetMethod(){
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();
        Method[] method = SimpleGetHandler.class.getDeclaredMethods();

        Invoke invoke = new Invoke(simpleGetHandler, method[0]);
        Response response = invoke.toRespondTo(new Request(method[0].getName(), "/simple_get", "HTTP/1.1",  new HashMap<>(), "Some test"));

        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void serverErrorWhenCantAccessMethod() {
        MethodNotAllowedExceptionStub methodNotAllowedExceptionStub = new MethodNotAllowedExceptionStub();
        Method[] method = MethodNotAllowedExceptionStub.class.getDeclaredMethods();

        Invoke invoke = new Invoke(methodNotAllowedExceptionStub, method[0]);
        Response response = invoke.toRespondTo(new Request("options", "/simple_get", "HTTP/1.1",  new HashMap<>(), "Some test"));

        assertThat(response.getStatusLine(), is("HTTP/1.1 500 Internal Server Error"));
    }
}
