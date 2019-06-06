package Util;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class FileReaderWrapperTest {

    @Test
    public void canReadAFile() throws IOException {
        String expectedBody = "<html>\n<body>\n<p>Find out if you are going to need wellies in your town!</p>\n</body></html>\n\n";
        assertThat(FileReaderWrapper.read("/Users/miss.merce/Workspace/Java/HttpServer/src/test/java/Public/Index.html"), is(expectedBody));
    }

}
