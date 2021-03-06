package Core;

import Request.Request;
import Response.Response;
import Util.PublicFileManager;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouterTest {
    private Request request;
    private String protocol = "HTTP/1.1";
    private PublicFileManager publicFileManager = new PublicFileManager("");
    private Router router = new Router(publicFileManager);

    @Test
    public void simpleGet() throws InvocationTargetException, IllegalAccessException {
        router.config();

        request = new Request("GET", "/simple_get", protocol, new HashMap<>(), "");
        Response response = router.route(request);
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void notFound() throws InvocationTargetException, IllegalAccessException {
        router.config();

        request = new Request("GET", "/simple_not_get", protocol, new HashMap<>(), "");
        Response response = router.route(request);
        assertThat(response.getStatusLine(), is("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void configuresTheRouter(){
        router.config();

        assertThat(router.getResources().isEmpty(), is(false));
    }


}