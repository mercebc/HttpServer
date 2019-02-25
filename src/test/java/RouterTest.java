import Handlers.Response;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouterTest {
    private Request request;
    private String protocol = "HTTP/1.1";
    private Router router = new Router();

    @Test
    public void simpleGet(){
        router.config();

        request = new Request("GET", "/simple_get", protocol, new HashMap<>(), "");
        Response response = router.route(request);
        assertThat(response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void notFound(){
        router.config();

        request = new Request("GET", "/simple_not_get", protocol, new HashMap<>(), "");
        Response response = router.route(request);
        assertThat(response.getStatusLine(), is("HTTP/1.1 404 Not Found"));
    }


    @Test
    public void configuresTheRouter(){
        router.config();
        assertThat(router.getRoutes().isEmpty(), is(false));
    }


}