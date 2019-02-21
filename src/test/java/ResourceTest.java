import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RouteTest {

    @Test
    public void twoRoutesAreEqual(){
        Route route = new Route("GET", "/example");
        Route secondRoute = new Route("GET", "/example");
        assertThat(route.equals(secondRoute), is(true));
    }

}