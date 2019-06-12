package Handlers;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResponseHandlerTest extends ResponseHandler{
    private ResponseHandlerTest responseHandler;

    @Before
    public void setUp(){
        responseHandler = new ResponseHandlerTest();
    }

    //@Test
    //public void canAddAHeader(){
      //  responseHandler.addHeader("header", "valueOfTheHeader");
        //assertThat(responseHandler.getHeaders().get("header"), is("valueOfTheHeader"));
    //}


}