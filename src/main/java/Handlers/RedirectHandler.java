package Handlers;


import Core.Request;
import Core.Response;
import Core.StatusLineBuilder;

public class RedirectHandler extends ResponseHandler {

    public Response get(Request request) {
        addHeader("Location", "http://0.0.0.0:5000/simple_get");
        return new Response(StatusLineBuilder.create(301), getHeaders());
    }
}
