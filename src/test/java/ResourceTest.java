import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResourceTest {

    @Test
    public void twoResourcesAreEqual(){
        Resource resource = new Resource("GET", "/example");
        Resource secondResource = new Resource("GET", "/example");
        assertThat(resource.equals(secondResource), is(true));
    }

    @Test
    public void twoResourcesAreDifferent(){
        Resource resource = new Resource("GET", "/example");
        Resource secondResource = new Resource("GET", "/example2");
        assertThat(resource.equals(secondResource), is(false));
    }

}