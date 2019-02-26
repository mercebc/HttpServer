package Handlers;

public class MethodOptions2Handler extends ResponseHandler {
    public Response get(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
    public Response head(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
    public Response options(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
    public Response put(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
    public Response post(Request request) {
        return new Response("HTTP/1.1 200 OK", getHeaders());
    }
}
