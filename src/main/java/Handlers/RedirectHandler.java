package Handlers;


import Request.Request;
import Response.Response;

public class RedirectHandler extends ResponseHandler {
    public Response get(Request request) {
        return Response.redirectTo("http://0.0.0.0:5000/simple_get");
    }
}
