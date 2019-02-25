package Handlers;

public class GetWithBodyHandler extends ResponseHandler {
    public Response head(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }
    public Response options(Request request) {
        return new Response("HTTP/1.1 200 OK", "");
    }
}
