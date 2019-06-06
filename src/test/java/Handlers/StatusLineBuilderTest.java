package Handlers;

import Core.StatusLineBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatusLineBuilderTest {

    @Test
    public void statusLineOK(){
        assertThat(StatusLineBuilder.create(200), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void statusLineMovedPermanently(){
        assertThat(StatusLineBuilder.create(301), is("HTTP/1.1 301 Moved Permanently"));
    }

    @Test
    public void statusLineNotFound(){
        assertThat(StatusLineBuilder.create(404), is("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void statusLineMethodNotAllowed(){
        assertThat(StatusLineBuilder.create(405), is("HTTP/1.1 405 Method Not Allowed"));
    }

}