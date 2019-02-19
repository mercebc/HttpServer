package Responses;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OKOldResponseTest {
    private OKOldResponse okResponse;

    @Before
    public void Setup() {
        okResponse = new OKOldResponse();
    }

    @Test
    public void getsCodeOfOKResponse() {
        assertThat(okResponse.getCode(), is(200));
    }

}