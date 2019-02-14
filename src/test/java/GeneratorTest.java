import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeneratorTest {
    private Request request;
    private String protocol = "HTTP/1.1";
    private Generator generator = new Generator();

    @Test
    public void simpleGet(){
        request = new Request("GET", "/simple_get", protocol);
        Response response = generator.generateResponse(request);
        assertThat(response.getCode(), is(200));
    }


}