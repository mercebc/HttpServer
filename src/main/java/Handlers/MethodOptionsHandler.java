package Handlers;

public class MethodOptionsHandler extends ResponseHandler {

    public Response get(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
    public Response head(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
    public Response options(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
}
