import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResourceTest {

    @Test
    public void twoRoutesAreEqual(){
        Resource resource = new Resource("GET", "/example");
        Resource secondResource = new Resource("GET", "/example");
        assertThat(resource.equals(secondResource), is(true));
    }

}