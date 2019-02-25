package Handlers;

public class MethodOptionsHandler extends ResponseHandler {
    public Response get(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }
    public Response head(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }
    public Response options(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }
}
