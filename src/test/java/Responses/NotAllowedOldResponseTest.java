package Responses;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotAllowedOldResponseTest {
    private NotAllowedOldResponse notAllowedResponse;

    @Before
    public void Setup() {
        notAllowedResponse = new NotAllowedOldResponse();
    }

    @Test
    public void getsCodeOfNotAllowedResponse() {
        assertThat(notAllowedResponse.getCode(), is(405));
    }
}