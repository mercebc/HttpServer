package Responses;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotFoundOldResponseTest {
    private NotFoundOldResponse notFoundResponse;

    @Before
    public void Setup() {
        notFoundResponse = new NotFoundOldResponse();
    }

    @Test
    public void getsCodeOfNotFoundResponse() {
        assertThat(notFoundResponse.getCode(), is(404));
    }
}