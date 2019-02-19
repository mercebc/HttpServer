import Responses.OldResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouterTest {
    private Request request;
    private String protocol = "HTTP/1.1";
    private Router router = new Router();

    @Test
    public void simpleGet(){
        request = new Request("GET", "/simple_get", protocol);
        Response response = router.route(request);
        assertThat(response.getHeaders(), is("HTTP/1.1 200 OK"));
    }

}